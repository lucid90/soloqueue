/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Vector;
import javax.swing.JProgressBar;
import mediator.Mediator;

/**
 *
 * @author LucianDobre
 */
public class DownloadTask extends Thread{
    public static final String STATE_SENDING = "UPLOADING";
    public static final String STATE_RECEIVING = "DOWNLOADING";
    public static final String STATE_COMPLETED = "FINISHED";
    Mediator med;
    String source;
    String destination;
    String fileName;
    JProgressBar progress;
    String downloadState;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public JProgressBar getProgress() {
        return progress;
    }

    public void setProgress(JProgressBar progress) {
        this.progress = progress;
    }

    public String getDownloadState() {
        return downloadState;
    }

    public void setDownloadState(String downloadState) {
        this.downloadState = downloadState;
    }

    public Mediator getMed() {
        return med;
    }

    public void setMed(Mediator med) {
        this.med = med;
    }
    
    public void finishState(){
        //med.removeDownloadTask(this);
    }
    
    public Vector toVector(){
        Vector toReturn = new Vector();
        toReturn.add(source);
        toReturn.add(destination);
        toReturn.add(fileName);
        toReturn.add(progress);
        toReturn.add(downloadState);
        
        return toReturn;
    }
    
    public void run(){
        progress.addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                med.getGui().getDownloadsTable().repaint();
            }
        });
        
        for ( int i = 0 ; i < 1000 ; ++i){
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
            }
            progress.setValue(i/10);
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
        }
        //med.removeDownloadTask(this);
    }
}
