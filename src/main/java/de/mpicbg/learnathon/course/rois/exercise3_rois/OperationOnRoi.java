package de.mpicbg.learnathon.course.rois.exercise3_rois;

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
import org.scijava.command.Command;
import org.scijava.convert.ConvertService;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

@Plugin( type = Command.class, menuPath = "Test > Invert")
public class OperationOnRoi implements Command
{
	@Parameter
	private ImagePlus image;

	@Parameter
	private ConvertService convertService;

	@Override
	public void run()
	{
		// Begin of the Exercise
		// 1. With ImageJFunctions convert ImagePlus to Img
		Img< ? extends NumericType<?> > img = null;
		// 2. Get the roi from the ImagePlus
		// 3. Use convertService to convert the Roi into a imglib2 RealMask
		// 4. Use method toIterableRegion to convert roiMask to an IterableRegion
		IterableRegion< ? > iterableROI = null;
		// End of the Exercise

		// Make an iterable image over only the samples contained in the ROI.
		final IterableInterval< ? extends NumericType<?> > iterableImageOnROI = Regions.sample( iterableROI, img );
		// Do something to each sample within the ROI.
		iterableImageOnROI.forEach(t -> t.mul( 0.5 ) );

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
		new ImageJ().ui().showUI();
	}
}
