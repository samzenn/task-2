import java.awt.*;

import javax.swing.*;

public class PictureFrame {
  public int[] reroll = null;
  Aardvark master = null;

  class DominoPanel extends JPanel {
    private static final long serialVersionUID = 4190229282411119364L;
    
    public static final int ColorCon = 20;
    public static final int DrawCentre = 30;

    public void drawGrid(Graphics g) {
      for (int are = 0; are < 7; are++) {
        for (int see = 0; see < 8; see++) {
		drawDigitGivenCentre(g, DrawCentre + see * ColorCon, DrawCentre + are * ColorCon, ColorCon,
              master.grid[are][see]);
        }
      }
    }

    public void drawGridLines(Graphics g) {
      g.setColor(Color.LIGHT_GRAY);
	for (int are = 0; are <= 7; are++) {
        g.drawLine(ColorCon, ColorCon + are * ColorCon, 180, ColorCon + are * ColorCon);
      }
      for (int see = 0; see <= 8; see++) {
        g.drawLine(ColorCon + see * ColorCon, ColorCon, ColorCon + see * ColorCon, 180);
      }
    }

    public void drawHeadings(Graphics g) {
	for (int are = 0; are < 7; are++) {
        fillDigitGivenCentre(g, 10, DrawCentre + are * ColorCon, ColorCon, are+1);
      }

      for (int see = 0; see < 8; see++) {
        fillDigitGivenCentre(g, DrawCentre + see * ColorCon, 10, ColorCon, see+1);
      }
    }

    public void drawDomino(Graphics g, Domino d) {
      if (d.placed) {
        int y = Math.min(d.ly, d.hy);
        int x = Math.min(d.lx, d.hx);
        int w = Math.abs(d.lx - d.hx) + 1;
        int h = Math.abs(d.ly - d.hy) + 1;
        g.setColor(Color.WHITE);
		g.fillRect(ColorCon + x * ColorCon, ColorCon + y * ColorCon, w * ColorCon, h * ColorCon);
        g.setColor(Color.RED);
        g.drawRect(ColorCon + x * ColorCon, ColorCon + y * ColorCon, w * ColorCon, h * ColorCon);
		drawDigitGivenCentre(g, DrawCentre + d.hx * ColorCon, DrawCentre + d.hy * ColorCon, ColorCon, d.high,
            Color.BLUE);
        drawDigitGivenCentre(g, DrawCentre + d.lx * ColorCon, DrawCentre + d.ly * ColorCon, ColorCon, d.low,
            Color.BLUE);
      }
    }

    void drawDigitGivenCentre(Graphics g, int x, int y, int diameter, int n) {
      int radius = diameter / 2;
      g.setColor(Color.BLACK);
      // g.drawOval(x - radius, y - radius, diameter, diameter);
      FontMetrics fm = g.getFontMetrics();
      String txt = Integer.toString(n);
      g.drawString(txt, x - fm.stringWidth(txt) / 2, y + fm.getMaxAscent() / 2);
    }

    void drawDigitGivenCentre(Graphics g, int x, int y, int diameter, int n,
        Color c) {
      int radius = diameter / 2;
      g.setColor(c);
      // g.drawOval(x - radius, y - radius, diameter, diameter);
      FontMetrics fm = g.getFontMetrics();
      String txt = Integer.toString(n);
      g.drawString(txt, x - fm.stringWidth(txt) / 2, y + fm.getMaxAscent() / 2);
    }

    void fillDigitGivenCentre(Graphics g, int x, int y, int diameter, int n) {
      int radius = diameter / 2;
      g.setColor(Color.GREEN);
      g.fillOval(x - radius, y - radius, diameter, diameter);
      g.setColor(Color.BLACK);
      g.drawOval(x - radius, y - radius, diameter, diameter);
      FontMetrics fm = g.getFontMetrics();
      String txt = Integer.toString(n);
      g.drawString(txt, x - fm.stringWidth(txt) / 2, y + fm.getMaxAscent() / 2);
    }

    protected void paintComponent(Graphics g) {
      g.setColor(Color.YELLOW);
      g.fillRect(0, 0, getWidth(), getHeight());

      // numbaz(g);
      //
      // if (master!=null && master.orig != null) {
      // drawRoll(g, master.orig);
      // }
      // if (reroll != null) {
      // drawReroll(g, reroll);
      // }
      //
      // drawGrid(g);
      if (master.mode == 1) {
        master.drawGuesses(g);
      }
      if (master.mode == 0) {
        master.drawDominoes(g);
      }
      drawGridLines(g);
      drawHeadings(g);
      drawGrid(g);
    }

    public Dimension getPreferredSize() {
      return new Dimension(202, 182);
    }
  }

  public DominoPanel dp;

  public void PictureFrame(Aardvark sf) {
    master = sf;
    if (dp == null) {
      JFrame f = new JFrame("Abominodo");
      dp = new DominoPanel();
      f.setContentPane(dp);
      f.pack();
      f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      f.setVisible(true);
    }
  }

  public void reset() {
    // TODO Auto-generated method stub

  }

}
