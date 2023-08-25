import java.io.*;
import java.net.*;

public class Receiver {
    public static void main(String[] args) {
        int port = 12345;

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Java server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();

                // Read XML data from Python
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String xmlData = reader.readLine();
                System.out.println("Received XML data from Python: " + xmlData);

                // Process XML and send response back to Python
                String response = processXML(xmlData);
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                writer.println(response);

                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String processXML(String xmlData) {
        int result = 1;
        String[] operands = xmlData.split("<operand>");
        for (int i = 1; i < operands.length; i++) {
            result *= Integer.parseInt(operands[i].substring(0, operands[i].indexOf("</operand>")));
        }
        return "<response>" + result + "</response>";
    }
}
