import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

class JOLPanel extends JPanel {

    private JLabel label;

    public JOLPanel() {
        setLayout(new BorderLayout());
        label = new JLabel("Pumpkin", JLabel.CENTER);
        label.setFont(new Font("dialog", Font.ITALIC, 40));
        add(label, BorderLayout.SOUTH);
    }

    public JLabel getLabel() {
        return label;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JOLPanel panel = new JOLPanel();
            frame.getContentPane().add(panel);

            frame.setSize(400, 400);
            frame.setLocation(200, 200);

            frame.setVisible(true);
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int w = getWidth();
        int h = getHeight();

        g2.setPaint(Color.orange);
        g2.draw(new Ellipse2D.Double(w / 16, h / 16, w * 7 / 8, h * 7 / 8));
        Ellipse2D.Double e = new Ellipse2D.Double(w / 4, h / 3, w * 3 / 16, h * 3 / 32);
        double x = e.getCenterX();
        double y = e.getCenterY();
        AffineTransform at = AffineTransform.getRotateInstance(Math.PI / 9, x, y);
        Shape leftEye = at.createTransformedShape(e);
        at.setToTranslation(w, 0);
        at.scale(-1, 1);
        Shape rightEye = at.createTransformedShape(leftEye);
        Area mouth = new Area(new Ellipse2D.Double(w / 4, h * 8 / 16, w / 2, h / 3));
        Area a = new Area(new Ellipse2D.Double(w / 8, h / 3, w * 3 / 4, h * 5 / 12));
        mouth.subtract(a);
        g2.draw(mouth);
        g2.setColor(Color.red);
        g2.draw(leftEye);
        g2.draw(rightEye);
        g2.setColor(Color.red);
    }
}
