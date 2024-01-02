import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

class Pumpkin extends JFrame {

   public void draw() {
      getContentPane().setLayout(new BorderLayout());
      getContentPane().add(new DrawPumpkin());
      getContentPane().add(getLabel(), BorderLayout.NORTH);
   }

   private JLabel getLabel() {
      JLabel label = new JLabel("PUMPKIN", JLabel.CENTER);
      label.setFont(new Font("dialog", Font.ITALIC | Font.BOLD, 20));
      return label;
   }
}

class DrawPumpkin extends JPanel {

   public static void main(String[] args) {
      Pumpkin obj = new Pumpkin();
      obj.draw();
      
      obj.setSize(700, 700);
      obj.setLocation(420, 100);

      obj.setVisible(true);
      obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;

      int w = getWidth();
      int h = getHeight();

      g2.setPaint(Color.orange);
      g2.fill(new Ellipse2D.Double(w / 16, h / 16, w * 7 / 8, h * 7 / 8));
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

      g2.setColor(Color.red);
      g2.fill(mouth);

      g2.setColor(Color.black);
      g2.fill(leftEye);
      g2.fill(rightEye);

      g2.setColor(Color.green);
      g2.fillOval(170, 100 / 4, 350, 100);
      g2.fillRect(320, 100 / 90, 50, 100);

      g2.setColor(Color.red);
      g2.fillRect(320, 100 * 3, 50, 100);
   }
}
