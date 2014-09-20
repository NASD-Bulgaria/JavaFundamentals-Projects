package com.course.congress.objects;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class HallArrangement implements Serializable {
	
	public static final Integer P_TYPE = 1;
	public static final Integer O_TYPE = 2;
	
	private static final HashMap<Integer, Image> arrangements = new HashMap<>();
	
	private Image image;
	
	static {
		try {
			arrangements.put(HallArrangement.P_TYPE, ImageIO.read(new File("image1.gif")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public HallArrangement(Integer type) {
		image = arrangements.get(type);
	}
	
}
