package br.inatel.labs.labmqtt.client;

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class SensorTemperaturaSubscriber {
    public static void main(String[] args){
        // 1.cria o subscriber
        String subscriberId = UUID.randomUUID().toString();
        IMqttClient subscriber = null;
        try {
            subscriber = new MqttClient(MyConstants.URI_BROKER, subscriberId);

            // 2.conecta
            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);
            subscriber.connect(options);
            System.out.println("Subscriber conectado e aguardando msg...");

            // 3.recebe a mensagem
            subscriber.subscribe(MyConstants.TOPIC_SENSOR, (topic, msg) -> {
                byte[] payload = msg.getPayload();
                System.out.println("Payload recebido: " + payload);
                System.out.println("Mensagem recebida: " + msg);
                System.out.println("Topico recebido: " + topic);
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                subscriber.close();
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }

    }
}