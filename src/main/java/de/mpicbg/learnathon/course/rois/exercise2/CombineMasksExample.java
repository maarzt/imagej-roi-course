package de.mpicbg.learnathon.course.rois.exercise2;

import bdv.util.BdvFunctions;
import bdv.util.BdvHandle;
import bdv.util.BdvOptions;
import bdv.util.BdvStackSource;
import de.mpicbg.learnathon.course.rois.utils.ExerciseUtils;
import net.imglib2.FinalInterval;
import net.imglib2.RealRandomAccessible;
import net.imglib2.img.Img;
import net.imglib2.roi.Masks;
import net.imglib2.roi.RealMask;
import net.imglib2.roi.geom.GeomMasks;
import net.imglib2.type.logic.BoolType;
import net.imglib2.type.numeric.ARGBType;

import java.io.IOException;

/**
 * Exercise Goal:
 *    You learn how to combine RealMasks using the logical operations:
 *     - RealMask.add(...)
 *     - RealMask.or(...)
 *     - RealMask.negate(...)
 *     - RealMask.minus(...)
 */
public class CombineMasksExample {

	public static void main(String... args) throws IOException {
		Img<?> image = ExerciseUtils.openBridgeImage();

		// Create some ROIs
		RealMask ellipse = GeomMasks.closedEllipsoid(new double[] { 200, 200 }, new double[] { 60, 150 });
		RealMask rectangle = GeomMasks.closedBox(new double[] { 200, 200 }, new double[] { 400, 370 });
		RealMask triangle = GeomMasks.closedPolygon2D(new double[] { 100, 600, 500 }, new double[] { 200, 150, 320 });

		// Show the ROIs
		showImageAndMask(image, ellipse);
		showImageAndMask(image, rectangle);
		showImageAndMask(image, triangle);

		// BEGIN OF THE EXERCISE
		// 1. Build a mask the contains all pixel of the ellipse and rectangle, but leaves out the triangle.
		RealMask mask = null;
		// 2. Use the method showImageAndMask, to show the mask you create in step 1.

		// END OF THE EXERCISE

		// Extra exercise: show all the rois ellipse, rectangle, triangle and mask from the exercise above in on Big Data Viewer window
	}

	private static void showImageAndMask(Img<?> image, RealMask mask)
	{
		BdvHandle bdv = BdvFunctions.show(image, "bridge", BdvOptions.options().is2D()).getBdvHandle();
		RealRandomAccessible< BoolType > img = Masks.toRealRandomAccessible(mask);
		BdvStackSource< BoolType > source = BdvFunctions.show(img, new FinalInterval(image), "mask", BdvOptions.options().addTo(bdv));
		source.setColor( new ARGBType(0xff0000));
	}
}
