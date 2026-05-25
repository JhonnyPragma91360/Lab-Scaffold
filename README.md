# Laboratorio APIFIRST - HandsOn_JsonToXML + java-mq-response

Este README describe como levantar el laboratorio completo en local:

1. Contenedor IBM MQ (QMGRLOCAL)
2. Proyecto java-mq-response (listener y respuesta MQ)
3. Proyecto HandsOn_JsonToXML (API que envia request MQ y transforma respuesta)

## 1) Prerrequisitos

- Windows con terminal bash (Git Bash o WSL)
- Podman instalado
- Java:
  - HandsOn_JsonToXML usa toolchain Java 21
  - java-mq-response usa sourceCompatibility Java 17
- No necesitas instalar Gradle globalmente (cada proyecto trae `gradlew`)

Puertos usados:

- IBM MQ: 1414 (MQ), 9443 (consola web)
- HandsOn_JsonToXML: 8080
- java-mq-response: 8081

## 2) Crear contenedor IBM MQ

Ejecuta este comando (podman):

```bash
podman run --name QMGRLOCAL \
  -p 1414:1414 \
  -p 9443:9443 \
  --env LICENSE=accept \
  --env MQ_QMGR_NAME=QMGRLOCAL \
  --detach \
  docker.io/ibmcom/mq:latest
```

Si el contenedor ya existe y esta detenido:

```bash
podman start QMGRLOCAL
```

Conceder permisos al usuario `app` para colas temporales (recomendado para request/reply):

```bash
podman exec -it QMGRLOCAL setmqaut -m QMGRLOCAL -n SYSTEM.DEFAULT.MODEL.QUEUE -t queue -p app +get +put +inq +dsp
```

Consola web MQ:

- URL: https://localhost:9443/ibmmq/console/login.html#
- Usuario: admin
- Password: passw0rd

## 3) Levantar java-mq-response

Abre una terminal y ejecuta:

```bash
cd java-mq-response
./gradlew clean :app-service:bootRun
```

Que hace este servicio:

- Escucha en la cola `DEV.QUEUE.1`
- Responde por request/reply hacia `DEV.QUEUE.2`
- Puerto HTTP de la app: 8081

Health check:

```bash
curl http://localhost:8081/actuator/health
```

## 4) Levantar HandsOn_JsonToXML

En otra terminal:

```bash
cd HandsOn_JsonToXML
./gradlew clean :app-service:bootRun
```

Que hace este servicio:

- Expone endpoint HTTP POST:
  - `/api/personas/consultadatospersona`
- Construye XML desde JSON
- Envia request MQ a `DEV.QUEUE.1`
- Espera respuesta y mapea XML de vuelta a JSON
- Puerto HTTP de la app: 8080

Health check:

```bash
curl http://localhost:8080/actuator/health
```

## 5) Prueba end-to-end

Con ambos servicios arriba y MQ activo, ejecuta:

```bash
curl -X POST "http://localhost:8080/api/personas/consultadatospersona" \
  -H "Content-Type: application/json" \
  -H "id-consumer: AW0611001" \
  -H "message-id: msg-001" \
  -d '{
    "data": {
      "identificacionCliente": {
        "tipoDocumento": "CC",
        "numeroDocumento": "123456789"
      }
    }
  }'
```

Respuesta esperada aproximada:

```json
{
  "meta": {
    "_messageId": "msg-001",
    "_consumerId": "AW0611001"
  },
  "data": {
    "informacionPersona": {
      "nombre": "Juliana Leon",
      "edad": 50
    }
  }
}
```

## 6) Generar artefactos JAR

java-mq-response:

```bash
cd java-mq-response
./gradlew clean :app-service:bootJar
java -jar applications/app-service/build/libs/JavaMqResponse.jar
```

HandsOn_JsonToXML:

```bash
cd HandsOn_JsonToXML
./gradlew clean :app-service:bootJar
java -jar applications/app-service/build/libs/ConsultaDatosPersona.jar
```

## 7) Detener todo

- Deten cada app con `Ctrl + C`
- Deten MQ:

```bash
podman stop QMGRLOCAL
```

Opcional (eliminar contenedor):

```bash
podman rm QMGRLOCAL
```

## 8) Troubleshooting rapido

- Error de conexion a MQ: valida que `QMGRLOCAL` este arriba y que el puerto 1414 este libre
- Timeout en request/reply: valida que `java-mq-response` este corriendo antes de llamar a HandsOn_JsonToXML
- Error Java version: usa Java 21 para HandsOn_JsonToXML y Java 17+ para java-mq-response
- Puerto ocupado: cambia `server.port` en cada `application.yaml`
