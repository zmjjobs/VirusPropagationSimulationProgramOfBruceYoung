package com.bruceyoung.util;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import com.bruceyoung.bean.Hospital;
import com.bruceyoung.bean.Person;

public class MyPanel extends JPanel implements Runnable {


	   private int pIndex=0;

	    public MyPanel() {
	        this.setBackground(new Color(0x444444));
	    }

	    @Override
	    public void paint(Graphics arg0) {
	        super.paint(arg0);
	        //draw border
	        arg0.setColor(new Color(0x00ff00));
	        arg0.drawRect(Hospital.getInstance().getX(),Hospital.getInstance().getY(),
	                Hospital.getInstance().getWidth(),Hospital.getInstance().getHeight());



	        List<Person> people = PersonPool.getInstance().getPersonList();
	        if(people==null){
	            return;
	        }
	        people.get(pIndex).update();
	        for(Person person:people){

	            switch (person.getState()){
	                case Person.State.NORMAL:{
	                    arg0.setColor(new Color(0xdddddd));

	                }break;
	                case Person.State.SHADOW:{
	                    arg0.setColor(new Color(0xffee00));

	                }break;
	                case Person.State.CONFIRMED:
	                case Person.State.FREEZE:{
	                    arg0.setColor(new Color(0xff0000));

	                }break;
	            }
	            person.update();
	            arg0.fillOval(person.getX(), person.getY(), 3, 3);

	        }
	        pIndex++;
	        if(pIndex>=people.size()){
	            pIndex=0;
	        }
	    }

	    public static int worldTime=0;
	    @Override
	    public void run() {
	        while (true) {

	            this.repaint();

	            try {
	                Thread.sleep(100);
	                worldTime++;
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }

	    }


	}
