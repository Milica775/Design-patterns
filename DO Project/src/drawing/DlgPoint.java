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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgPoint extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtX;
	private JTextField txtY;

	private boolean ok;
	private Color col=Color.green;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgPoint dialog = new DlgPoint();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgPoint() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Point");
		contentPanel.setBackground(Color.cyan);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setModal(true); //prekida se rad programa i sve dok korisnik ne klikne na ok ili cancel
		
		JLabel lblX = new JLabel("X:");		
		JLabel lblY = new JLabel("Y:");
		
		txtX = new JTextField();
		txtX.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char t=e.getKeyChar();  //u promjenljivu se smjesta svaki znak koji korisnik ukuca
				if(t=='-') {            //ako je korisnik ukucao -
					e.consume();        //pojede ga
					getToolkit().beep(); //zvuk upozorenja

				}
			}
		});
		
		txtX.setColumns(10);
		txtY = new JTextField();
		txtY.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char t=e.getKeyChar();
				if(t=='-') {
					e.consume();
					getToolkit().beep();
				}
			}
		});
		txtY.setText("");
		txtY.setColumns(10);
		
		JButton btnColor = new JButton("Color");
		btnColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				col=JColorChooser.showDialog(null, "Odaberite boju tacke", null);
				btnColor.setBackground(col);
			
			}
		});
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblX)
							.addGap(18)
							.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblY)
							.addGap(18)
							.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnColor))
					.addContainerGap(300, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblX)
						.addComponent(txtX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblY)
						.addComponent(txtY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnColor)
					.addContainerGap(138, Short.MAX_VALUE))
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

	public boolean isOk() {
		return ok;
	}

	public Color getCol() {
		return col;
	}

	public void setCol(Color col) {
		this.col = col;
	}

	public void setTxtX(String txtX) {
		this.txtX.setText(txtX);
	}

	public void setTxtY(String txtY) {
		this.txtY.setText(txtY);
	}

	public void setTxtYEditable(boolean b) {
		this.txtY.setEditable(b);
	}

	public void setTxtXEditable(boolean b) {
		this.txtX.setEditable(b);
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public String getTxtX() {
		return txtX.getText();
	}

	public String getTxtY() {
		return txtY.getText();
	}
}
