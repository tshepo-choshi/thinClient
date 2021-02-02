/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datatire;

import Library.ClientObj;
import Library.Transmission;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author eliasc
 */
public class DataTire {
   
    public DataTire() throws IOException {
        ServerSocket server = new ServerSocket(8001);
        while(true){
            Socket socket = server.accept();
            Thread thread = new Thread(new Request(socket));
            thread.start();
        }
    }

    public class Request implements Runnable{
        Socket socket;
        public Request(Socket socket){
            this.socket = socket;
        }
        @Override
        public void run() {
            Dao dao = new Dao();
            try{
                dao.openDB();
                
                ObjectInputStream readFromClient = new ObjectInputStream(socket.getInputStream());
                Transmission trans = (Transmission) readFromClient.readObject();
                ObjectOutputStream writeToClient = new ObjectOutputStream(socket.getOutputStream());
                
                ClientObj client = (ClientObj) trans.getObject();
                String theRequest = trans.getDecision();
                if(theRequest.equalsIgnoreCase("login")){
                    ClientObj clientObj = dao.login(client);
                    if(clientObj != null){
                        trans.setDecision("success");
                    }else{
                        trans.setDecision("User not found on the System");
                    }                    
                    writeToClient.writeObject(trans);
                    
                }else if(theRequest.equalsIgnoreCase("add")){
                    int test = dao.add(client);
                    if(test > 0){
                        trans.setDecision("Client successful");
                    }else{
                        trans.setDecision("Error, could not register client");
                    }
                    writeToClient.writeObject(trans);
                
                }else if(theRequest.equalsIgnoreCase("delete")){
                    int clientNo = (Integer) trans.getObject();
                    int test = dao.delete(clientNo);
                    if(test > 0){
                        trans.setDecision("Client Deleted");
                    }else{
                        trans.setDecision("Error, could not delete");
                    }
                    writeToClient.writeObject(trans);
                
                }else if(theRequest.equalsIgnoreCase("update")){
                    int test = dao.update(client);
                    if(test > 0){
                        trans.setDecision("Client details updated successfully");
                    }else{
                        trans.setDecision("Error, could not update client details");
                    }
                    writeToClient.writeObject(trans);
                
                }else if(theRequest.equalsIgnoreCase("search")){
                    int clientNo = (Integer) trans.getObject();
                    ClientObj clientObj = dao.search(clientNo);
                    if(clientObj != null){
                        trans.setDecision("success");
                        trans.setObject(clientObj);
                    }else{
                        trans.setDecision("User not found on the System");
                    }                    
                    writeToClient.writeObject(trans);                    
                }
                
                dao.closeDB();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            }          
            
        }
    }
    public static void main(String[] args) throws IOException {
        new DataTire();
    }
    
}
