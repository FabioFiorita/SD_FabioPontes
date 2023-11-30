package br.inatel.labs.labmqtt.client;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.Random;
import java.util.UUID;

public class SensorTemperaturaPublisher {
    public static void main(String[] args) {
        IMqttClient publisher = null;
        try {
            // 1.criar o publisher
            String publisherId = UUID.randomUUID().toString();
            publisher = new MqttClient(MyConstants.URI_BROKER, publisherId);

            // 3.connecta
            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);
            publisher.connect(options);

            for (int i = 0; i < 10; i++) {

                // 2.cria a mensagem
                MqttMessage msg = getTemperatureMessage();
                msg.setQos(0);
                msg.setRetained(true);

                // 4.publica
                publisher.publish(MyConstants.TOPIC_SENSOR, msg);

                Thread.sleep(2000);
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                // 5.desconecta
                publisher.disconnect();
                publisher.close();
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }

    }
    private static MqttMessage getTemperatureMessage() {
        Random r = new Random();
        double temperatura = 80 + r.nextDouble() * 20.0;
        byte[] payload = String.format("T:%04.2f", temperatura).getBytes();
        return new MqttMessage(payload);
    }

}
