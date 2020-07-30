package barbero;

import java.awt.EventQueue; 
import javax.swing.JFrame;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import java.awt.TextArea;
import java.awt.Label;

//import barbero.barbero;

public class barbero {

	public JFrame frame;

	
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					barbero window = new barbero();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public barbero() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 498, 473);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JProgressBar progressBar_1 = new JProgressBar();
		progressBar_1.setOrientation(SwingConstants.VERTICAL);
		progressBar_1.setBounds(171, 161, 11, 121);
		frame.getContentPane().add(progressBar_1);
		
		JProgressBar progressBar_2 = new JProgressBar();
		progressBar_2.setOrientation(SwingConstants.VERTICAL);
		progressBar_2.setBounds(324, 161, 11, 121);
		frame.getContentPane().add(progressBar_2);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setOrientation(SwingConstants.VERTICAL);
		progressBar.setBounds(25, 161, 11, 121);
		frame.getContentPane().add(progressBar);
		
		JLabel lblNewLabel_5 = new JLabel("Clientes atendidos por Barbero1");
		lblNewLabel_5.setBounds(10, 323, 217, 31);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Clientes atendidos por Barbero2");
		lblNewLabel_6.setBounds(253, 323, 219, 31);
		frame.getContentPane().add(lblNewLabel_6);
		
		TextArea textArea = new TextArea();
		textArea.setEditable(false);
		textArea.setBounds(39, 10, 413, 132);
		frame.getContentPane().add(textArea);		
		
		Bshop shop = new Bshop();
		 
	    Barber barber = new Barber(shop,1, progressBar_1, progressBar, lblNewLabel_5, textArea);
	    
	    Barber barber2 = new Barber(shop,2, progressBar_2, progressBar, lblNewLabel_6, textArea);
	    
	    CustomerGenerator cg = new CustomerGenerator(shop, progressBar, textArea);

	    Thread thbarber = new Thread(barber);
	    Thread thbarber2 = new Thread(barber2);
	    
	    Thread thcg = new Thread(cg);
	    
	    //thcg.start();
	    // thbarber2.start();
	    //thbarber.start();
		
		JButton btnNewButton_2 = new JButton("start");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Thread thbarber2 = new Thread(barber);
				thbarber2.start();
			}
		});
		btnNewButton_2.setBounds(345, 186, 107, 31);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnStop_1 = new JButton("stop");
		btnStop_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					thbarber2.stop();
			
			}
		});
		btnStop_1.setBounds(345, 228, 107, 31);
		frame.getContentPane().add(btnStop_1);
		
		JButton btnStart = new JButton("start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Thread thbarber = new Thread(barber);
				thbarber.start();
			}
		});
		
		btnStart.setBounds(192, 186, 107, 31);
		frame.getContentPane().add(btnStart);
		
		JButton btnStop = new JButton("stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					thbarber.stop();
			
			}
		});
		btnStop.setBounds(192, 228, 107, 31);
		frame.getContentPane().add(btnStop);
		
		JLabel lblNewLabel = new JLabel("Barbero \u00B01");
		lblNewLabel.setBounds(213, 161, 77, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblBarbero = new JLabel("Barbero \u00B02");
		lblBarbero.setBounds(360, 161, 77, 14);
		frame.getContentPane().add(lblBarbero);
		
		JButton button = new JButton("start");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Thread thcg = new Thread(cg);
				thcg.start();
			}
		});
		button.setBounds(46, 186, 107, 31);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("stop");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thcg.stop();
				
			}
		});
		button_1.setBounds(46, 228, 107, 31);
		frame.getContentPane().add(button_1);
		
		JLabel lblNewLabel_1 = new JLabel("Clientes");
		lblNewLabel_1.setBounds(78, 161, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);		
		
		JLabel lblNewLabel_2 = new JLabel("Sala de espera");
		lblNewLabel_2.setBounds(35, 283, 86, 20);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Energ\u00EDa- Barbero 1");
		lblNewLabel_3.setBounds(192, 286, 107, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Energ\u00EDa- Barbero 2");
		lblNewLabel_4.setBounds(334, 286, 118, 14);
		frame.getContentPane().add(lblNewLabel_4);
					
	}
}