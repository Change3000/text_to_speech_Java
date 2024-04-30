import javax.speech.*;
import java.util.*;
import javax.speech.synthesis.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class TextToSpeech
{
    //text to listen
    String speaktext;
    JFrame f;


    public void dospeak(String voicename)
    {


        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        String speak;

        f = new JFrame("Text-To-Speech");
        JLabel l = new JLabel("YOUR VOICE");
        l.setBounds(300,40,150,200);
        l.setForeground(Color.GRAY);

        JTextArea a1 = new JTextArea();
        a1.setBounds(50,20,50,30);
        a1.setBackground(Color.white);
        a1.setForeground(Color.BLACK);


        JButton b1 = new JButton("Listen");
        b1.setBounds(300,400,100,30);
        b1.setForeground(Color.black);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                speaktext=a1.getText();;
                a1.getText();

                String voiceName =voicename;
        try
        {

            SynthesizerModeDesc desc = new SynthesizerModeDesc(null, "general",  Locale.US, null, null);

            Synthesizer synthesizer =  Central.createSynthesizer(desc);

            synthesizer.allocate();

            synthesizer.resume();
            desc = (SynthesizerModeDesc)  synthesizer.getEngineModeDesc();
            Voice[] voices = desc.getVoices();
            Voice voice = null;

            for (int i = 0; i < voices.length; i++)
            {
                if (voices[i].getName().equals(voiceName))
                {
                    voice = voices[i];
                    break;
                }
            }
            synthesizer.getSynthesizerProperties().setVoice(voice);
            System.out.print("Speaking: "+speaktext);
            synthesizer.speakPlainText(speaktext, null);
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
            synthesizer.deallocate();
        }
        catch (Exception e1)
        {
            String message = " missing speech.properties in " + System.getProperty("user.home") + "\n";
            System.out.println(""+e1);
            System.out.println(message);
        }}});

        f.add(b1);
        f.add(l);
        f.add(a1);
        //f.add(p);
        f.setSize(700,800);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.getContentPane().setBackground(Color.BLACK);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

    }
    public static void main(String args[])
    {
        TextToSpeech obj=new TextToSpeech();
        obj.dospeak( "kevin16");
    }
}