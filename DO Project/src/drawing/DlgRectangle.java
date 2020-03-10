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

public class DlgRectangle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUpperLeftPointX;
	private JTextField txtUpperLeftPointY;
	private JTextField txtHeight;
	private JTextField txtWidth;
	private Color interCol=Color.MAGENTA;
	private Color exterCol=Color.BLACK;
	private boolean ok;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgRectangle dialog = new DlgRectangle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgRectangle() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Rectangle");
		contentPanel.setBackground(Color.cyan);
		this.setModal(true);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblUpperLeftPointX = new JLabel("UpperLeftPointX:");
		JLabel lblUpperLeftPointY = new JLabel("UpperLeftPointY:");
		JLabel lblHeight = new JLabel("Height:");
		JLabel lblWidth = new JLabel("Width:");
		txtUpperLeftPointX = new JTextField();
		txtUpperLeftPointX.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char t=e.getKeyChar();
				if(t=='-') {
					e.consume();
					getToolkit().beep();
				}
			}
		});
		txtUpperLeftPointX.setColumns(10);
		txtUpperLeftPointY = new JTextField();
		txtUpperLeftPointY.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char t=e.getKeyChar();
				if(t=='-') {
					e.consume();
					getToolkit().beep();
				}
			}
		});
		txtUpperLeftPointY.setText("");
		txtUpperLeftPointY.setColumns(10);
		txtHeight = new JTextField();
		txtHeight.setText("");
		txtHeight.setColumns(10);
		txtWidth = new JTextField();
		txtWidth.setText("");
		txtWidth.setColumns(10);
		
		JButton btnInteriorColor = new JButton("Interior color");
	    btnInteriorColor.setBackground(interCol);
		btnInteriorColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				interCol=JColorChooser.showDialog(null, "Odaberite boju unutrasnjosti pravougaonika", null);
				btnInteriorColor.setBackground(interCol);
			}
		});
		
		JButton btnExteriorColor = new JButton("Exterior color");
		btnExteriorColor.setBackground(exterCol);
		btnExteriorColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exterCol=JColorChooser.showDialog(null, "Odaberite boju ivica pravougaonika", null);
				btnExteriorColor.setBackground(exterCol);
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblUpperLeftPointX)
							.addGap(18)
							.addComponent(txtUpperLeftPointX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblUpperLeftPointY)
								.addComponent(lblHeight)
								.addComponent(lblWidth))
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtUpperLeftPointY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(btnInteriorColor)
						.addComponent(btnExteriorColor))
					.addContainerGap(228, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUpperLeftPointX)
						.addComponent(txtUpperLeftPointX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUpperLeftPointY)
						.addComponent(txtUpperLeftPointY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHeight)
						.addComponent(txtHeight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblWidth)
						.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnInteriorColor)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnExteriorColor)
					.addContainerGap(51, Short.MAX_VALUE))
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
						try
						{
						setOk(true);
						dispose();
						}
						catch(NumberFormatException ex)
						{
							JOptionPane.showMessageDialog(new JFrame(), "Popunite sva polja ili provjerite tip podataka koji ste unijeli!", "Greska", JOptionPane.WARNING_MESSAGE);
						
						} catch (Exception e1) {
							
							JOptionPane.showMessageDialog(new JFrame(), "Visina i sirina moraju biti pozitivne!", "Greska", JOptionPane.WARNING_MESSAGE);
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

	public void setTxtUpperLeftPointXEditable(boolean a) {
		this.txtUpperLeftPointX.setEditable(a);
	}
	public void setTxtUpperLeftPointYEditable(boolean a) {
		this.txtUpperLeftPointY.setEditable(a);
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public String getTxtUpperLeftPointX() {
		return txtUpperLeftPointX.getText();
	}

	public void setTxtUpperLeftPointX(String txtUpperLeftPointX) {
		this.txtUpperLeftPointX.setText(txtUpperLeftPointX);
	}
	
	public String getTxtUpperLeftPointY() {
		return txtUpperLeftPointY.getText();
	}

	public void setTxtUpperLeftPointY(String txtUpperLeftPointY) {
		this.txtUpperLeftPointY.setText(txtUpperLeftPointY);
	}
	public String getTxtHeight() {
		return txtHeight.getText();
	}

	public void setTxtHeight(String txtHeight) {
		this.txtHeight.setText(txtHeight);
	}
	public String getTxtWidth() {
		return txtWidth.getText();
	}

	public void setTxtWidth(String txtWidth) {
		this.txtWidth.setText(txtWidth);
	}

	public Color getInterCol() {
		return interCol;
	}

	public void setInterCol(Color inerCol) {
		this.interCol = inerCol;
	}

	public Color getExterCol() {
		return exterCol;
	}

	public void setExterCol(Color exterCol) {
		this.exterCol = exterCol;
	}

}
