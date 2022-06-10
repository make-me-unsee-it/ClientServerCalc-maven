import java.io.*;
import java.net.Socket;
import java.util.Objects;

import org.apache.log4j.Logger;

public class Client {
    private Socket clientSocket;

    private BufferedReader in;
    private BufferedWriter out;
    private static final Logger LOG = Logger.getLogger(Client.class);

    String launch(String currentTask) {
        String serverWord = "null";
        try {
            try {
                clientSocket = new Socket("localhost", 4004);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                LOG.info("client socket, read and write streams started");

                LOG.info("user input is: " + currentTask);
                out.write(currentTask + "\n");
                out.flush();
                LOG.info("input sent to server");

                serverWord = in.readLine();
                LOG.info("result: " + serverWord);

            } finally {
                clientSocket.close();
                in.close();
                out.close();
                LOG.info("client and its read and write streams closed");
            }
        } catch (IOException e) {
            LOG.info("client error!");
        }
        return serverWord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client client)) return false;
        return clientSocket.equals(client.clientSocket)
                && in.equals(client.in) && out.equals(client.out);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientSocket, in, out);
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientSocket=" + clientSocket +
                ", in=" + in +
                ", out=" + out +
                '}';
    }
}
