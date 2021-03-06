package de.mpicbg.learnathon.course.rois.exercise4;

import de.mpicbg.learnathon.course.rois.utils.ExerciseUtils;
import ij.ImagePlus;
import ij.gui.Overlay;
import net.imagej.Dataset;
import net.imagej.ImageJ;
import net.imagej.roi.DefaultROITree;
import net.imagej.roi.ROITree;
import net.imglib2.img.Img;
import net.imglib2.img.display.imagej.ImageJFunctions;
import net.imglib2.roi.MaskPredicate;
import net.imglib2.roi.geom.GeomMasks;
import org.scijava.convert.ConvertService;
import org.scijava.ui.UIService;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Exercise Goals:
 *   You learn how to convert RealMasks into ImageJ1 Rois.
 *   You learn how to display RealMasks with ImageJ1.
 */
public class DisplayWithIJ1Example
{
	public static void main(String... args) throws IOException
	{
		// Setup ImageJ and services
		ImageJ imageJ = new ImageJ();
		ConvertService convertService = imageJ.convert();
		UIService uiService = imageJ.ui();
		// open image
		Img<?> image = ExerciseUtils.openBridgeImage();

		// create rois
		List< MaskPredicate< ? > > rois = Arrays.asList(
				GeomMasks.polygon2D( new double[]{10, 80, 80, 20, 20, 10}, new double[]{10, 10, 20, 20, 80, 80} ),
				GeomMasks.closedBox( new double[]{30, 30}, new double[]{40, 60} ),
				GeomMasks.polygon2D( new double[]{50, 60, 60, 30, 30, 50}, new double[]{30, 30, 80, 80, 70, 70} ),
				GeomMasks.closedBox( new double[]{70, 30}, new double[]{80, 60} )
		);

		// BEGIN OF THE EXERCISE
		// 1. Create new DefaultROITree.
		ROITree roiTree = null;
		// 2. Add rois to the newly created ROITree.

		// 3. Convert the ROITree to Overlay using the ConvertService
		Overlay overlay = null;
		// 4. Convert and the image to ImagePlus using ImageJFunctions
		ImagePlus imagePlus = null;
		// 5. Use setOverlay on the ImagePlus

		// 6. Use the uiService to show the ImagePlus

		// END OF THE EXERCISE
	}
}
