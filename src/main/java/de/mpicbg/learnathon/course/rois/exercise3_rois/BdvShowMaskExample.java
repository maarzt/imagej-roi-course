package de.mpicbg.learnathon.course.rois.exercise3_rois;

import bdv.util.BdvFunctions;
import bdv.util.BdvHandle;
import bdv.util.BdvOptions;
import bdv.util.BdvStackSource;
import ij.ImagePlus;
import net.imglib2.Interval;
import net.imglib2.RealRandomAccessible;
import net.imglib2.img.Img;
import net.imglib2.img.display.imagej.ImageJFunctions;
import net.imglib2.roi.Masks;
import net.imglib2.roi.RealMaskRealInterval;
import net.imglib2.roi.geom.GeomMasks;
import net.imglib2.type.logic.BoolType;
import net.imglib2.type.numeric.ARGBType;
import net.imglib2.util.Intervals;

public class BdvShowMaskExample {

	public static void main(String... args) {
		// Mask
		RealMaskRealInterval mask = GeomMasks.closedEllipsoid(new double[] { 120, 120, 60 }, new double[] { 30, 60, 50 });

		// Load image of a human head. Image size is 255x255x128 pixels.
		Img< ? > image = ImageJFunctions.wrap(new ImagePlus("https://imagej.net/images/t1-head.zip"));

		// Show image in bdv
		BdvStackSource< ? > headSource = BdvFunctions.show(image, "head");
		headSource.setDisplayRange(0, 500);
		BdvHandle bdv = headSource.getBdvHandle();

		// Show mask
		RealRandomAccessible< BoolType > img = Masks.toRealRandomAccessible(mask);
		Interval interval = Intervals.smallestContainingInterval(mask);
		BdvFunctions.show( img, interval, "mask", BdvOptions.options().addTo(bdv) ).setColor(new ARGBType(0x770000));
	}
}
