package de.mpicbg.learnathon.course.rois.exercise3_rois;

import bdv.util.BdvFunctions;
import bdv.util.BdvHandle;
import bdv.util.BdvOptions;
import bdv.util.BdvStackSource;
import io.scif.SCIFIO;
import net.imagej.Dataset;
import net.imglib2.Interval;
import net.imglib2.RealRandomAccessible;
import net.imglib2.roi.Masks;
import net.imglib2.roi.RealMaskRealInterval;
import net.imglib2.roi.geom.GeomMasks;
import net.imglib2.type.logic.BoolType;
import net.imglib2.type.numeric.ARGBType;
import net.imglib2.util.Intervals;

import java.io.IOException;

public class CombineMasksExample {

	public static void main(String... args) throws IOException {

		RealMaskRealInterval ellipsoid = GeomMasks.closedEllipsoid(new double[] { 200, 200 }, new double[] { 60, 150 });
		RealMaskRealInterval rectangle = GeomMasks.closedBox(new double[] { 200, 200 }, new double[] { 400, 370 });
		RealMaskRealInterval triangle = GeomMasks.closedPolygon2D(new double[] { 100, 600, 500 }, new double[] { 200, 150, 320 });

		BdvHandle bdv = showBrideImageWithBdv();
		showMask(bdv, ellipsoid, new ARGBType(0x770000));
		showMask(bdv, rectangle, new ARGBType(0x007700));
		showMask(bdv, triangle, new ARGBType(0x000077));
	}

	private static void showMask(BdvHandle bdv, RealMaskRealInterval mask,
			ARGBType color)
	{
		RealRandomAccessible< BoolType > img = Masks.toRealRandomAccessible(mask);
		Interval interval = Intervals.smallestContainingInterval(mask);
		BdvFunctions.show( img, interval, "mask", BdvOptions.options().addTo(bdv) ).setColor( color);
	}

	private static BdvHandle showBrideImageWithBdv() throws IOException {
		// Load image of a human head. Image size is 255x255x128 pixels.
		String path = DisplayRoi.class.getResource( "/bridge.tif" ).getFile();
		Dataset image = new SCIFIO().datasetIO().open( path );

		// Show image in bdv
		BdvStackSource< ? > headSource = BdvFunctions
				.show(image, "head", BdvOptions.options().is2D());
		headSource.setDisplayRange(0, 255);
		return headSource.getBdvHandle();
	}
}
