import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

import exception.UserInputException;
import org.apache.log4j.Logger;

public class Server {
    private Socket clientSocket;
    private ServerSocket server;
    private BufferedReader in;
    private BufferedWriter out;
    private static final Logger LOG = Logger.getLogger(Server.class);

    public void run() {
        try {
            try {
                server = new ServerSocket(4004);
                LOG.info("new server is running and waiting for a connection");
                clientSocket = server.accept();
                LOG.info("clientside " + clientSocket.getInetAddress() + " connected");

                try {
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                    LOG.info("server read and write threads started");

                    String word = in.readLine();
                    LOG.info("received message: " + word);

                    out.write(new Calc().run(word) + "\n");
                    out.flush();
                    LOG.info("the result is sent to the client");

                } catch (UserInputException e) {
                    e.printStackTrace();
                    LOG.info("calculation problem - exception");

                } finally {
                    in.close();
                    out.close();
                    LOG.info("server read and write streams closed");
                }

            } finally {
                clientSocket.close();
                server.close();
                LOG.info("socket and server closed");
            }

        } catch (IOException e) {
            LOG.info("server error!");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Server server1)) return false;
        return clientSocket.equals(server1.clientSocket)
                && server.equals(server1.server) && in.equals(server1.in) && out.equals(server1.out);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientSocket, server, in, out);
    }

    @Override
    public String toString() {
        return "Server{" +
                "clientSocket=" + clientSocket +
                ", server=" + server +
                ", in=" + in +
                ", out=" + out +
                '}';
    }
}

