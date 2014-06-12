package gui;

import java.net.*;
import java.net.URL;
import javax.swing.*;
import javax.swing.event.*;

public class HelpWindow {
    URL thisAppletsCodeBase;
    JFrame frame;

    // AppletContext thisAppletContext;

    // public HelpWindow(URL urlParam AppletContext context)
    // {
    // thisAppletsCodeBase=urlParam;
    // thisAppletContext=context;
    // }

    public HelpWindow(URL urlParam) {
        thisAppletsCodeBase = urlParam;
    }

    public void showHelpWindow() {

        JTabbedPane tabPane = new JTabbedPane();

        // TP1
        JTextPane tp1 = new JTextPane();
        JScrollPane sp1 = new JScrollPane(tp1);
        tabPane.addTab("Inhalt", null, sp1,
                "Was macht der Roboter auf dem Neptun?");
        try {
            tp1.setPage(new URL(thisAppletsCodeBase, "HelpWindow/inhalt.html"));
        } catch (Exception e) {
            System.out.println("HelpWindow: No access to the html files");
            tp1.setText(new String(
                    "Oi, autsch! Du brauchst eine Internetverbindung, um hier was zu sehen!"));
            e.printStackTrace();
        }

        // TP2
        JTextPane tp2 = new JTextPane();
        JScrollPane sp2 = new JScrollPane();
        sp2.getViewport().add(tp2);
        tabPane.addTab("Hilfe zum Spiel", null, sp2,
                "Wie koennen wir dem Roboter helfen, Diamanten zu sammeln?");
        try {
            tp2.setPage(new URL(thisAppletsCodeBase,
                    "HelpWindow/spielhilfe.html"));
        } catch (Exception e) {
            tp2.setText(new String(
                    "Oi, autsch! Du brauchst eine Internetverbindung, um hier was zu sehen!"));
        }

        // TP3
        JTextPane tp3 = new JTextPane();
        JScrollPane sp3 = new JScrollPane();
        sp3.getViewport().add(tp3);
        tabPane.addTab("Ueber 'Labyrinth auf dem Neptun'", null, sp3,
                "Wie ist dieses Spiel entstanden?");
        try {
            // HTMLDocument doc = (HTMLDocument)pane.getDocument();
            tp3.addHyperlinkListener(new HyperlinkListener() {
                public void hyperlinkUpdate(HyperlinkEvent event) {
                    /*
                     * if ( event.getEventType() ==
                     * HyperlinkEvent.EventType.ACTIVATED ) { AppletContext
                     * context = getAppletContext(); try {
                     * context.showDocument(new URL(URL.getText()), "_blank");
                     * }catch (MalformedURLException ex) { } }
                     */
                }
            });
            tp3.setPage(new URL(thisAppletsCodeBase, "HelpWindow/ueber.html"));

        } catch (Exception e) {
            tp3.setText(new String(
                    "Oi, autsch! Du brauchst eine Internetverbindung, um hier was zu sehen!"));
        }

        // window
        frame = new JFrame("Hilfe");
        frame.getContentPane().add(tabPane);
        frame.setSize(800, 600);
        frame.setVisible(true);

        // AppletContext Context = getAppletContext();
        // try
        // {
        // Context.showDocument(new URL(Url.getText()), "_blank");
        // } catch (MalformedURLException ex) { }

    }
}
