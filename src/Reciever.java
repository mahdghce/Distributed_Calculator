import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


//args[0]    host port number


public class Reciever
{
    public static void main(String[] args) throws IOException
    {
        ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0]));
        Socket clientsocket = serverSocket.accept();
        PrintWriter out = new PrintWriter(clientsocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
        String input_line;
        while ((input_line = in.readLine()) != null)
        {
            System.out.println("massage received:"+input_line);
            //process the message
            float result=0;
            boolean check=false;
            long startTime = System.nanoTime();
            int first_dolor=input_line.indexOf('$');
            int second_dolor=input_line.indexOf('$',first_dolor+1);
            int third_dolor=input_line.indexOf('$',second_dolor+1);
            int forth_dolor=input_line.indexOf('$',third_dolor+1);
            if((first_dolor == -1)||(second_dolor==-1)||(third_dolor==-1))
            {
                out.println("this is illegal input");
                continue;
            }
            String operator=input_line.substring(first_dolor+1,second_dolor);
            String operand1=input_line.substring(second_dolor+1,third_dolor);
            String operand2="";
            if (forth_dolor != -1)
                operand2=input_line.substring(third_dolor+1,forth_dolor);
            if (operator.equals("Add"))
            {
                check=true;
                result=Integer.parseInt(operand1)+Integer.parseInt(operand2);
                System.out.println(result);
            }
            if (operator.equals("Subtract"))
            {
                check=true;
                result=Integer.parseInt(operand1)-Integer.parseInt(operand2);
            }
            if (operator.equals("Divide"))
            {
                check=true;
                if (operand2.equals("0"))
                {
                    out.println("this is illegal");
                    continue;
                }
                result=Integer.parseInt(operand1)/Integer.parseInt(operand2);
            }
            if (operator.equals("Multiply"))
            {
                check=true;
                result=Integer.parseInt(operand1)*Integer.parseInt(operand2);
            }
            if (operator.equals("Sin"))
            {
                check=true;
                result= (float) Math.sin(Integer.parseInt(operand1));
            }
            if (operator.equals("Cos"))
            {
                check=true;
                result= (float) Math.cos(Integer.parseInt(operand1));
            }
            if (operator.equals("Tan"))
            {
                check=true;
                result= (float) Math.tan(Integer.parseInt(operand1));
            }
            if (operator.equals("Cot"))
            {
                check=true;
                result= (float)(1/(Math.tan(Integer.parseInt(operand1))));
            }
            if (!check)
                {
                    out.println("this is illegal input ");
                    continue;
                }
            long endTime   = System.nanoTime();
            long totalTime = endTime - startTime;
            out.println("$"+totalTime+" nono second"+"$"+result+"$");
        }
    }
}
