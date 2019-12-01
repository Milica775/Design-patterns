


package hexagon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestHexagon extends JPanel
{
    public static void main(final String[] args) {
        final   TestHexagon pnl = new  TestHexagon();
        final JFrame f = new JFrame();
        f.getContentPane().add(pnl, "Center");
        f.setSize(800, 600);
        f.setDefaultCloseOperation(3);
        f.setVisible(true);
    }
    
    @Override
    public void paint(final Graphics g) {
        super.paint(g);
        final Hexagon h = new Hexagon(100, 100, 50);
        h.setAreaColor(Color.RED);
        h.setBorderColor(Color.YELLOW);
        h.setSelected(true);
        h.paint(g);
    }
}
