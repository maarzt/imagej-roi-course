package de.mpicbg.learnathon.course.rois.exercise1;

import bdv.util.BdvFunctions;
import bdv.util.BdvHandle;
import bdv.util.BdvOptions;
import bdv.util.BdvStackSource;
import de.mpicbg.learnathon.course.rois.utils.ExerciseUtils;
import net.imagej.Dataset;
import net.imglib2.Interval;
import net.imglib2.RealRandomAccessible;
import net.imglib2.img.Img;
import net.imglib2.roi.Masks;
import net.imglib2.roi.RealMaskRealInterval;
import net.imglib2.roi.geom.GeomMasks;
import net.imglib2.type.logic.BoolType;
import net.imglib2.type.numeric.ARGBType;
import net.imglib2.type.numeric.RealType;
import net.imglib2.util.Intervals;

import java.io.IOException;

/**
 * Exercise Goal:
 *     You will learn how to display a RealMask with Big Data Viewer.
 */
public class BdvShowMaskExample {

	public static void main(String... args) throws IOException {

		// Open image
		Img<?> image = ExerciseUtils.openBridgeImage();

		// Create a sphere shaped roi
		RealMaskRealInterval roi = GeomMasks.closedSphere(new double[] { 200, 200 }, 100);

		// Show image in bdv

		// BEGIN OF THE EXERCISE
		// 1. Use BdvFunctions.show to show the image.
		//    Assign the returned BdvStackSource to a variable.
		//    Make sure you provide BdvOptions.options().is2D() as an argument.
		BdvStackSource< ? > source = BdvFunctions.show(image, "image", BdvOptions.options().is2D());
		// 2. From the BdvStackSource get the BdvHandle
		BdvHandle bdv = source.getBdvHandle();

		// Show mask
		// 3. Use the method Masks.toRealRandomAccessible to convert the roi into a RealRandomAccessible
		RealRandomAccessible< BoolType > randomAccessible = Masks.toRealRandomAccessible(roi);
		// 4. Use the method Intervals.smallestContainingInterval to get the interval from the roi
		Interval interval = Intervals.smallestContainingInterval(roi);
		// 5. Use BdvFunctions.show to show the randomAccessible. Hint: You need to use the interval from above too.
		BdvStackSource< BoolType > roiSource = BdvFunctions.show(randomAccessible, interval, "roi", BdvOptions.options().addTo(bdv));
		// 6. Set the color of the roiSource to red.
		roiSource.setColor(new ARGBType(0xff0000));
		// Extra: 7. In step 5 above, add BdvOption.options().addTo(bdv) as argument, to show the roi and the image in the same Big Data Viewer window.
		// END OF THE EXERCISE
	}

}
