package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DlgLine extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtStartPointX;
	private JTextField txtStartPointY;
	private JTextField txtEndPointX;
	private JTextField txtEndPointY;
	private Color col;
	private boolean ok;
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgLine dialog = new DlgLine();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgLine() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		setModal(true);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Line");
		contentPanel.setBackground(Color.cyan);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblStartPointX = new JLabel("StartPointX:");
		txtStartPointX = new JTextField();
		txtStartPointX.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char t=e.getKeyChar();
				if(t=='-') {
					e.consume();
					getToolkit().beep();
				}
			}
		});
		txtStartPointX.setColumns(10);
		JLabel lblStartPointY = new JLabel("StartPointY:");
		txtStartPointY = new JTextField();
		txtStartPointY.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char t=e.getKeyChar();
				if(t=='-') {
					e.consume();
					getToolkit().beep();
				}
			}
		});
		txtStartPointY.setColumns(10);
		JLabel lblEndPointX = new JLabel("EndPointX:");
		txtEndPointX = new JTextField();
		txtEndPointX.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char t=e.getKeyChar();
				if(t=='-') {
					e.consume();
					getToolkit().beep();
				}
			}
		});
		txtEndPointX.setText("");
		txtEndPointX.setColumns(10);
		JLabel lblEndPointY = new JLabel("EndPointY:");
		txtEndPointY = new JTextField();
		txtEndPointY.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char t=e.getKeyChar();
				if(t=='-') {
					e.consume();
					getToolkit().beep();
				}
			}
		});
		txtEndPointY.setText("");
		txtEndPointY.setColumns(10);
		
		JButton btnColor = new JButton("Color");
		btnColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				col=JColorChooser.showDialog(null, "Odaberite boju linije", null);				
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblStartPointX)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(txtStartPointX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblStartPointY)
							.addGap(10)
							.addComponent(txtStartPointY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblEndPointX)
							.addGap(18)
							.addComponent(txtEndPointX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblEndPointY)
							.addGap(18)
							.addComponent(txtEndPointY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnColor))
					.addContainerGap(258, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStartPointX)
						.addComponent(txtStartPointX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtStartPointY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStartPointY))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEndPointX)
						.addComponent(txtEndPointX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEndPointY)
						.addComponent(txtEndPointY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnColor)
					.addContainerGap(80, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {							
							setOk(true);
							dispose();
							}
							catch(NumberFormatException a)
							{
								JOptionPane.showMessageDialog(new JFrame(), "Popunite sva polja ili provjerite tip podataka koji ste unijeli!", "Greska!", JOptionPane.ERROR_MESSAGE);
							}
					}
				});
			
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}



	public void setTxtEndPointYEditable(boolean b) {
		this.txtEndPointY.setEditable(b);
	}

	public void setTxtEndPointXEditable(boolean b) {
		this.txtEndPointX.setEditable(b);
	}
	
	public void setTxtStartPointYEditable(boolean b) {
		this.txtStartPointY.setEditable(b);
	}
	public void setTxtStartPointXEditable(boolean b) {
		this.txtStartPointX.setEditable(b);
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public String getTxtEndPointY() {
		return txtEndPointY.getText();
	}

	public void setTxtEndPointY(String txtEndPointY) {
		this.txtEndPointY.setText(txtEndPointY);
	}

	public String getTxtEndPointX() {
		return txtEndPointX.getText();
	}

	public void setTxtEndPointX(String txtEndPointX) {
		this.txtEndPointX.setText(txtEndPointX);
	}
	public String getTxtStartPointX() {
		return txtStartPointX.getText();
	}

	public void setTxtStartPointX(String txtStartPointX) {
		this.txtStartPointX.setText(txtStartPointX);
	}
	public String getTxtStartPointY() {
		return txtStartPointY.getText();
	}

	public void setTxtStartPointY(String txtStartPointY) {
		this.txtStartPointY.setText(txtStartPointY);
	}

	public Color getCol() {
		return col;
	}

	public void setCol(Color col) {
		this.col = col;
	}

}
