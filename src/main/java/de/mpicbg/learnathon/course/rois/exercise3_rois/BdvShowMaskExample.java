package de.mpicbg.learnathon.course.rois.exercise3_rois;

import bdv.util.BdvFunctions;
import bdv.util.BdvHandle;
import bdv.util.BdvOptions;
import bdv.util.BdvStackSource;
import ij.ImagePlus;
import io.scif.SCIFIO;
import net.imagej.Dataset;
import net.imagej.ImageJ;
import net.imglib2.Interval;
import net.imglib2.RealRandomAccessible;
import net.imglib2.img.Img;
import net.imglib2.img.display.imagej.ImageJFunctions;
import net.imglib2.roi.Masks;
import net.imglib2.roi.RealMaskRealInterval;
import net.imglib2.roi.geom.GeomMasks;
import net.imglib2.type.logic.BoolType;
import net.imglib2.type.numeric.ARGBType;
import net.imglib2.type.numeric.RealType;
import net.imglib2.util.Intervals;

import java.io.IOException;

public class BdvShowMaskExample {

	public static void main(String... args) throws IOException {
		// Mask
		RealMaskRealInterval mask = GeomMasks.closedSphere(new double[] { 200, 200 }, 100);

		// open image
		String path = DisplayRoi.class.getResource( "/bridge.tif" ).getFile();
		Dataset image = new ImageJ().scifio().datasetIO().open(path);

		// Show image in bdv
		BdvStackSource< ? > headSource = BdvFunctions.show(image, "image", BdvOptions.options().is2D());
		BdvHandle bdv = headSource.getBdvHandle();

		// Show mask
		RealRandomAccessible< BoolType > img = Masks.toRealRandomAccessible(mask);
		Interval interval = Intervals.smallestContainingInterval(mask);
		BdvFunctions.show( img, interval, "mask", BdvOptions.options().addTo(bdv) ).setColor(new ARGBType(0x770000));
	}
}
