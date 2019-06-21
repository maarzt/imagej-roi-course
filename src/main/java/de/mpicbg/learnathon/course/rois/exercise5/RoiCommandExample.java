package de.mpicbg.learnathon.course.rois.exercise5;

import de.mpicbg.learnathon.course.rois.utils.ExerciseUtils;
import ij.ImagePlus;
import ij.gui.Roi;
import net.imagej.ImageJ;
import net.imglib2.Interval;
import net.imglib2.IterableInterval;
import net.imglib2.RandomAccessible;
import net.imglib2.img.Img;
import net.imglib2.img.display.imagej.ImageJFunctions;
import net.imglib2.roi.IterableRegion;
import net.imglib2.roi.Masks;
import net.imglib2.roi.RealMask;
import net.imglib2.roi.Regions;
import net.imglib2.type.logic.BoolType;
import net.imglib2.type.numeric.NumericType;
import net.imglib2.view.IntervalView;
import net.imglib2.view.Views;
import org.scijava.ItemIO;
import org.scijava.command.Command;
import org.scijava.convert.ConvertService;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

/**
 * Exercise Goals:
 *   You learn, how to write a ImageJ command, that:
 *   1. Uses a region of interest as input.
 *   2. Performs it's image processing with ImgLib2.
 */
@Plugin( type = Command.class, menuPath = "Test > Imprint Region Of Interest")
public class RoiCommandExample implements Command
{
	@Parameter(type = ItemIO.BOTH)
	private ImagePlus image;

	@Parameter
	private ConvertService convertService;

	@Override
	public void run()
	{
		// BEGIN OF THE EXERCISE
		// 1. With ImageJFunctions convert ImagePlus to Img
		Img< ? extends NumericType<?> > img = null;
		// 2. Get the roi from the ImagePlus
		Roi roi = null;
		// 3. Use convertService to convert the Roi into a imglib2 RealMask
		RealMask roiMask = null;
		// 4. Use method toIterableRegion to convert roiMask to an IterableRegion
		IterableRegion< BoolType > iterableROI = null;
		// END OF THE EXERCISE

		// Make an iterable image over only the pixels contained in the ROI.
		final IterableInterval< ? extends NumericType<?> > iterableImageOnROI = Regions.sample( iterableROI, img );
		// Do something to each pixel within the ROI.
		iterableImageOnROI.forEach(t -> t.mul( 0.5 ) );

		// Signal to the GUI that the image has changed.
		image.updateAndDraw();
	}

	private IterableRegion< BoolType > toIterableRegion( RealMask mask, Interval image )
	{
		// Convert ROI from R^n to Z^n.
		final RandomAccessible<BoolType > discreteROI = Views.raster( Masks.toRealRandomAccessible(mask) );
		// Apply finite bounds to the discrete ROI.
		final IntervalView<BoolType> boundedDiscreteROI = Views.interval(discreteROI, image);
		// Create an iterable version of the finite discrete ROI.
		return Regions.iterable(boundedDiscreteROI);
	}

	public static void main(String... args) {
		ImageJ imageJ = new ImageJ();
		imageJ.ui().showUI();
		imageJ.ui().show(ExerciseUtils.openBridgeImage());
	}
}
