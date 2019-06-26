package de.mpicbg.learnathon.course.rois.exercise3;

import bdv.util.BdvFunctions;
import bdv.util.BdvOptions;
import de.mpicbg.learnathon.course.rois.utils.ExerciseUtils;
import net.imglib2.Interval;
import net.imglib2.IterableInterval;
import net.imglib2.RandomAccessible;
import net.imglib2.RealRandomAccessible;
import net.imglib2.img.Img;
import net.imglib2.roi.IterableRegion;
import net.imglib2.roi.Masks;
import net.imglib2.roi.RealMask;
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

		// Create a sphere shaped ROI
		RealMaskRealInterval mask = GeomMasks.closedSphere(new double[] { 200, 200 }, 100);

		// BEGIN OF THE EXERCISE
		// 1. Use the method toIterableRegion to convert the mask into an IterableRegion.
		IterableRegion< BoolType > iterableRegion = null;
		// 2. Use Regions.sample to get an Iterable that iterates over the pixels of the bridge image in the iterable region.
		IterableInterval< ? extends RealType<?>> iterable = null;
		// 3. Iterate over the iterable and set all pixel to zero.
		//    Hint: There are two options to solve this: 1. Use a for loop. 2. Use iterable.forEach and lambdas.

		// 4. Show the image with BdvFunctions.show . Don't forget to use BdvOptions.options().is2D() .

		// END OF THE EXERCISE
	}

	private static IterableRegion< BoolType > toIterableRegion( RealMask mask, Interval image )
	{
		// Convert ROI from R^n to Z^n.
		final RandomAccessible<BoolType > discreteROI = Views.raster( Masks.toRealRandomAccessible(mask) );
		// Apply finite bounds to the discrete ROI.
		final IntervalView<BoolType> boundedDiscreteROI = Views.interval(discreteROI, image);
		// Create an iterable version of the finite discrete ROI.
		return Regions.iterable(boundedDiscreteROI);
	}

}
