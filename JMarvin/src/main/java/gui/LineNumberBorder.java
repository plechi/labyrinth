package gui;

// This is the class written by some programmer

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LineNumberBorder extends AbstractBorder {
    private Color numberColor;

    public LineNumberBorder() {
    }

    public LineNumberBorder(Color lineNumberColor) {
        numberColor = lineNumberColor;
    }

    public static void main(String args[]) {
        JFrame jf = new JFrame();
        Container cp = jf.getContentPane();
        cp.setLayout(new BorderLayout());
        JTextArea ta = new JTextArea();
        ta.setBorder(new LineNumberBorder(Color.red));
        // ta.setLineWrap(true);
        cp.add(new JScrollPane(ta), BorderLayout.CENTER);
        jf.setSize(300, 400);
        jf.setVisible(true);
        jf.addWindowListener(new WindowAdapter() {
            public void windowActivated(WindowEvent e) {
            }

            public void windowClosed(WindowEvent e) {
            }

            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }

            public void windowDeactivated(WindowEvent e) {
            }

            public void windowDeiconified(WindowEvent e) {
            }

            public void windowIconified(WindowEvent e) {
            }

            public void windowOpened(WindowEvent e) {
            }
        });

    }

    /**
     * Paints the border for the specified component with the specified position
     * and size.
     *
     * @param c
     *            the component for which this border is being painted
     * @param g
     *            the paint graphics
     * @param x
     *            the x position of the painted border
     * @param y
     *            the y position of the painted border
     * @param width
     *            the width of the painted border
     * @param height
     *            the height of the painted border
     */
    public void paintBorder(Component c, Graphics g, int x, int y, int width,
            int height) {
        if (!(c instanceof JTextArea)) {
            return;
        }
        Insets insets = getBorderInsets(c);
        Color oldColor = g.getColor();
        if (numberColor == null)
            numberColor = c.getForeground();
        g.setColor(numberColor);
        g.translate(x, y);

        JTextArea ta = (JTextArea) c;

        // Mask the left margin area
        Graphics cg = g.create();
        cg.setClip(0, insets.top, insets.left, height - insets.top);
        Font f = ta.getFont();
        FontMetrics fm = cg.getFontMetrics(f);
        int lines = ta.getLineCount();
        for (int i = 0; i < lines; i++) {
            try {
                Rectangle r = ta.modelToView(ta.getLineStartOffset(i));
                int lx = insets.left - fm.stringWidth("W" + (i + 1));
                int ly = r.y + fm.getLeading() + fm.getMaxAscent();
                cg.drawString("" + (i + 1), lx, ly);
            } catch (BadLocationException ble) {
            }
        }
        cg.dispose();
        g.setColor(oldColor);
    }

    /**
     * Returns the insets of the border.
     *
     * @param c
     *            the component for which this border insets value applies
     */
    public Insets getBorderInsets(Component c) {
        if (!(c instanceof JTextArea)) {
            return new Insets(0, 0, 0, 0);
        }

        FontMetrics fm = c.getFontMetrics(c.getFont());
        int margin = fm.stringWidth("WWWWWW");
        return new Insets(0, margin, 0, 0);
    }

    /**
     * Returns whether or not the border is opaque. If the border is opaque, it
     * is responsible for filling in it's own background when painting.
     */
    public boolean isBorderOpaque() {
        return false;
    }

} // LineNumberBorder