package de.mpicbg.learnathon.course.rois.exercise3;

import bdv.util.BdvFunctions;
import bdv.util.BdvOptions;
import de.mpicbg.learnathon.course.rois.utils.ExerciseUtils;
import net.imglib2.Interval;
import net.imglib2.IterableInterval;
import net.imglib2.RealRandomAccessible;
import net.imglib2.img.Img;
import net.imglib2.roi.IterableRegion;
import net.imglib2.roi.Masks;
import net.imglib2.roi.RealMaskRealInterval;
import net.imglib2.roi.Regions;
import net.imglib2.roi.geom.GeomMasks;
import net.imglib2.type.logic.BoolType;
import net.imglib2.type.numeric.RealType;
import net.imglib2.util.Intervals;
import net.imglib2.view.IntervalView;
import net.imglib2.view.RandomAccessibleOnRealRandomAccessible;
import net.imglib2.view.Views;

/**
 * Exercise Goals:
 *   1. You learn how to convert a RealMask into a IterableRegion.
 *   2. You learn how to use IterableRegion to write a very simple image operation.
 */
public class IterableRegionsExample {

	public static void main(String... args) {
		// Open image
		Img<? extends RealType<?> > image = ExerciseUtils.openBridgeImage();

		// Create a sphere shaped roi
		RealMaskRealInterval roi = GeomMasks.closedSphere(new double[] { 200, 200 }, 100);

		// BEGIN OF THE EXERCISE
		// 1. Use BdvFunctions.show to show the image, assign the returned BdvStackSource to a variable.
		IterableRegion< BoolType > iterableRegion = toIterableRegion(roi);
		// 2. Use Regions.sample to get an Iterable that iterates over the pixels of the bridge image in the iterable region.
		IterableInterval< ? extends RealType<?>> iterable = Regions.sample(iterableRegion, image);
		// 3. Iterate over the iterable and set all pixel to zero.
		//    Hint: There are two options to solve this: 1. Use a for loop. 2. Use iterable.forEach and lambdas.
		for( RealType<?> pixel : iterable )
			pixel.setZero();
		// 4. Show the image with BdvFunctions.show . Don't forget to use BdvOptions.options().is2D() .
		BdvFunctions.show(image, "bridge", BdvOptions.options().is2D());
		// END OF THE EXERCISE
	}

	private static IterableRegion< BoolType > toIterableRegion(
			RealMaskRealInterval roi)
	{
		RealRandomAccessible< BoolType > rra = Masks.toRealRandomAccessible(roi);
		RandomAccessibleOnRealRandomAccessible< BoolType > ra = Views.raster(rra);
		Interval interval = Intervals.largestContainedInterval(roi);
		IntervalView< BoolType > rai = Views.interval(ra, interval);
		return Regions.iterable(rai);
	}

}
