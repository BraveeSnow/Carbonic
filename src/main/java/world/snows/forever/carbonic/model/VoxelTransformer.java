package world.snows.forever.carbonic.model;

import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.util.FastMath;

/**
 * Powered by linear algebra!
 */
public class VoxelTransformer {
    private RealMatrix transformMatrix;
    private final VoxelShape voxels;

    public VoxelTransformer(VoxelShape voxels) {
        this.transformMatrix = MatrixUtils.createRealIdentityMatrix(4);
        this.voxels = voxels;
    }

    /**
     * Modifies the current transformation matrix to rotate about the Y axis by
     * a given factor.
     *
     * @param rad The rotation about the Y axis in radians.
     */
    public VoxelTransformer rotateY(double rad) {
        final double[][] data = {
                {FastMath.cos(rad), 0, FastMath.sin(rad), 0},
                {0, 1, 0, 0},
                {-FastMath.sin(rad), 0, FastMath.cos(rad), 0},
                {0, 0, 0, 1}
        };
        this.transformMatrix = this.transformMatrix.multiply(MatrixUtils.createRealMatrix(data));
        return this;
    }


    /**
     * Processes the given VoxelShape with the current transformation matrix.
     *
     * @return The newly transformed VoxelShape.
     */
    public VoxelShape getTransformedVoxels() {
        VoxelShape generatedShape = Shapes.empty();

        for (AABB box : this.voxels.toAabbs()) {
            final double[] min = {box.minX - 0.5, box.minY - 0.5, box.minZ - 0.5, 1};
            final double[] max = {box.maxX - 0.5, box.maxY - 0.5, box.maxZ - 0.5, 1};
            final double[] newMin = this.transformMatrix.multiply(MatrixUtils.createColumnRealMatrix(min)).getColumn(0);
            final double[] newMax = this.transformMatrix.multiply(MatrixUtils.createColumnRealMatrix(max)).getColumn(0);

            for (int i = 0; i < 3; i++) {
                if (newMin[i] > newMax[i]) {
                    newMin[i] += newMax[i];
                    newMax[i] = newMin[i] - newMax[i];
                    newMin[i] -= newMax[i];
                }
            }

            generatedShape = Shapes.join(generatedShape, Shapes.box(newMin[0] + 0.5, newMin[1] + 0.5, newMin[2] + 0.5,
                    newMax[0] + 0.5, newMax[1] + 0.5, newMax[2] + 0.5), BooleanOp.OR);
        }

        return generatedShape;
    }
}
