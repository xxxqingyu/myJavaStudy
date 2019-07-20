package app.PipedStream;

import java.io.IOException;
import java.io.PipedInputStream;

public class Receiver extends Thread {
    private PipedInputStream in = new PipedInputStream();

    public PipedInputStream getInputStream(){
        return in;
    }

    @Override
    public void run() {
        super.run();
    }

    public void readMessageOne() {
        byte[] buf =new byte[2048];
        try{
            int len = in.read(buf);
            System.out.println(new String(buf,0,len));
            in.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}