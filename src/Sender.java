import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Sender
{
    public static void main(String[] args) throws IOException
    {
        Socket socket = new Socket(args[0],Integer.parseInt(args[1]));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("please enter your data:");
        String user_input;
        while((user_input=stdin.readLine())!= null)
        {
            out.println(user_input);
            String Server_data;
            Server_data=in.readLine();
            System.out.println(Server_data);
            System.out.println("please enter your data:");
        }
    }
}

