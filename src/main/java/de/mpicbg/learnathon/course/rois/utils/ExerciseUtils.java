package de.mpicbg.learnathon.course.rois.utils;

import de.mpicbg.learnathon.course.rois.exercise4.DisplayWithIJ1Example;
import net.imagej.ImageJ;
import net.imglib2.img.Img;
import net.imglib2.type.numeric.RealType;

import java.io.IOException;

public class ExerciseUtils {

	public static Img<? extends RealType<?> > openBridgeImage() {
		try {
			String path = ExerciseUtils.class.getResource( "/bridge.tif" ).getFile();
			return new ImageJ().scifio().datasetIO().open(path).getImgPlus().getImg();
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
