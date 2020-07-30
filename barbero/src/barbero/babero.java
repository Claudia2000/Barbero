package barbero;
import java.awt.Label;
import java.awt.TextArea;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class babero {
	public static void main(String a[])
    {
        Bshop shop = new Bshop();
 
        //Barber barber = new Barber(shop,1,);
        //Barber barber2 = new Barber(shop,2);
        //CustomerGenerator cg = new CustomerGenerator(shop);
 
        //Thread thbarber = new Thread(barber);
        //Thread thbarber2 = new Thread(barber2);
        //Thread thcg = new Thread(cg);
        //thcg.start();
        //thbarber2.start();
        //thbarber.start();
    }
}


class Barber implements Runnable
{
    Bshop shop;
    int nume;
    JProgressBar progressBar__1;
    JProgressBar cliente6;
    int total=5;
    JLabel texto;
    TextArea textote;
    public Barber(Bshop shop,int num, JProgressBar progressBar_1, JProgressBar cliente5, JLabel blNewLabel_5,TextArea textito)
    {
        this.shop = shop;
        nume=num;
        progressBar__1=progressBar_1;
        progressBar__1.setMinimum(0);
        progressBar__1.setMaximum(20-1);
        cliente6=cliente5;
        texto=blNewLabel_5;
        textote=textito;
    }
    public void run()
    {
        try
        {
            Thread.sleep(1000);
        }
        catch(InterruptedException iex)
        {
            iex.printStackTrace();
        }
        textote.append("Barbero "+nume+" comenzara.."+"\n");
        int variable=0;
        while(total>0)
        {
            shop.cutHair(nume, progressBar__1, cliente6, textote);
            total--;
            variable++;
            texto.setText("Clientes atendidos "+variable);
        }
    }
}
class Customer implements Runnable
{
    String name;
    Date inTime;
    int n=0;
    Bshop shop; 
    TextArea textote;
    public Customer(Bshop shop, TextArea textote4)
    {
        this.shop = shop;
        textote=textote4;
    }
 
    public String getName() {
        return name;
    }
 
    public Date getInTime() {
        return inTime;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }
 
    public void run()
    {
        goForHairCut();
    }
    private synchronized void goForHairCut()
    {
        shop.add(this, textote);
    }
}
class CustomerGenerator implements Runnable
{
    Bshop shop;
    JProgressBar cliente;
    TextArea textote;
    public CustomerGenerator(Bshop shop,JProgressBar progressBar, TextArea textote3)
    {
        this.shop = shop;
        cliente=progressBar;
        cliente.setMinimum(0);
        cliente.setMaximum(3);
        textote=textote3;
    }
 
    public void run()
    {
    	int i=0;
        while(true)
        {
        	i=i+1;
        	cliente.setValue(i);
            Customer customer = new Customer(shop, textote);
            customer.setInTime(new Date());
            Thread thcustomer = new Thread(customer);
            customer.setName("Cliente Thread "+thcustomer.getId());
            thcustomer.start();
 
            try
            {
                TimeUnit.SECONDS.sleep((long)(Math.random()*10));
            }
            catch(InterruptedException iex)
            {
                iex.printStackTrace();
            }
        }
    }
 
}
 
class Bshop
{
    int sillas;
    List<Customer> listCustomer;
 
    public Bshop()
    {
        sillas = 3;
        listCustomer = new LinkedList<Customer>();
    }
 
    public void cutHair(int val,JProgressBar progressBar__1, JProgressBar cliente7, TextArea textote) 
    {
        Customer customer;
        textote.append("Barebero "+val+ " esperando para cortar."+"\n");
        int i=19;
        int aleatorio=0;
        progressBar__1.setValue(i);
        synchronized (listCustomer)
        {
        	
            while(listCustomer.size()==0)
            {
            	progressBar__1.setValue(i+1);
                textote.append("Barbero "+val+ " esta esperando al cliente."+"\n");
                
                try
                {
                    listCustomer.wait();
                }
                catch(InterruptedException iex)
                {
                    iex.printStackTrace();
                }
            }
            aleatorio = (int)(Math.random()*10+1);
            progressBar__1.setValue(i-aleatorio);
            textote.append("Barbero "+val+" encontro un cliente en la cola."+"\n");
            customer = (Customer)((LinkedList<?>)listCustomer).poll();
        }
        long duration=0;
        try
        {   
        	
        	textote.append("Corte de pelo a la cliente : "+customer.getName()+"\n");
            duration = (long)(Math.random()*10);
            TimeUnit.SECONDS.sleep(duration);
        }
        catch(InterruptedException iex)
        {
            iex.printStackTrace();
        }
        
        cliente7.setValue(cliente7.getValue()-1);
        textote.append("Corte de pelo completado a la cliente : "+customer.getName() + " en "+duration+ " segundos."+"\n");
        
    }
 
    public void add(Customer customer, TextArea textote2)
    {
        textote2.append("cliente : "+customer.getName()+ " entro a la tienda."+"\n");
        synchronized (listCustomer)
        {
            if(listCustomer.size() == sillas)
            {
            	textote2.append("no hay silla disponible para el cliente  "+customer.getName()+"\n");
            	textote2.append("cliente "+customer.getName()+" salio..."+"\n");
                return ;
            }
            
            ((LinkedList<Customer>)listCustomer).offer(customer);
            textote2.append("Cliente : "+customer.getName()+ " tiene la silla."+"\n");
             
            if(listCustomer.size()==1)
                listCustomer.notify();
        }
    }
}