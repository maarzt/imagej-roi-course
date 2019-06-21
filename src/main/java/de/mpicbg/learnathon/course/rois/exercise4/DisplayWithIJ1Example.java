package de.mpicbg.learnathon.course.rois.exercise4;

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
		// setup ImageJ and Services
		ImageJ imageJ = new ImageJ();
		ConvertService convertService = imageJ.convert();
		UIService uiService = imageJ.ui();
		// open image
		String path = DisplayWithIJ1Example.class.getResource( "/bridge.tif" ).getFile();
		Dataset image = imageJ.scifio().datasetIO().open( path );

		// create rois
		List< MaskPredicate< ? > > rois = Arrays.asList(
				GeomMasks.polygon2D( new double[]{10, 80, 80, 20, 20, 10}, new double[]{10, 10, 20, 20, 80, 80} ),
				GeomMasks.closedBox( new double[]{30, 30}, new double[]{40, 60} ),
				GeomMasks.polygon2D( new double[]{50, 60, 60, 30, 30, 50}, new double[]{30, 30, 80, 80, 70, 70} ),
				GeomMasks.closedBox( new double[]{70, 30}, new double[]{80, 60} )
		);

		// BEGIN OF THE EXERCISE
		// 1. Create new DefaultROITree.
		ROITree roiTree = new DefaultROITree();
		// 2. Add rois to the newly created ROITree.
		roiTree.addROIs( rois );
		// 3. Convert the ROITree to Overlay using the ConvertService
		Overlay overlay = convertService.convert( roiTree, Overlay.class );
		// 4. Convert and the image to ImagePlus
		ImagePlus imagePlus = ImageJFunctions.wrap( (Img) image, "bridge" );
		// 5. Use setOverlay on the ImagePlus
		imagePlus.setOverlay( overlay );
		// 6. Show the ImagePlus
		imagePlus.show();
		// END OF THE EXERCISE
	}
}
