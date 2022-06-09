import java.io.*;
import java.net.Socket;
import java.util.Objects;

import org.apache.log4j.Logger;

public class Client {
    private Socket clientSocket;
    private BufferedReader reader;
    private BufferedReader in;
    private BufferedWriter out;
    private static final Logger LOG = Logger.getLogger(Server.class);

    public Client() {
    }

    String run() {
        String serverWord = "null";
        try {
            try {
                clientSocket = new Socket("localhost", 4004);
                LOG.info("new client - connection established");
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                LOG.info("client read and write threads started");

                System.out.println("Что сказать серверу?");
                reader = new BufferedReader(new InputStreamReader(System.in));
                String word = reader.readLine();
                LOG.info("the user entered a word: " + word);

                out.write(word + "\n");
                out.flush();
                LOG.info("input sent to server");

                serverWord = in.readLine();

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
                && reader.equals(client.reader) && in.equals(client.in) && out.equals(client.out);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientSocket, reader, in, out);
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientSocket=" + clientSocket +
                ", reader=" + reader +
                ", in=" + in +
                ", out=" + out +
                '}';
    }
}
