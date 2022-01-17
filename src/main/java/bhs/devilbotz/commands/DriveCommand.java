/*-------------------------------------------------------------------------------*/
/* Copyright (c) 2021-2022 BHS Devilbotz. All Rights Reserved.                   */
/* Open Source Software - may be modified, commercialized, distributed,          */
/* sub-licensed and used for private use under the terms of the License.md       */
/* file in the root of the source code tree.                                     */
/*                                                                               */
/* You MUST include the original copyright and license files in any and all      */
/* revised/modified code. You may NOT remove this header under any circumstance  */
/* unless explicitly noted                                                       */
/*-------------------------------------------------------------------------------*/

package bhs.devilbotz.commands;

import bhs.devilbotz.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.CommandBase;

import java.util.function.DoubleSupplier;

public class DriveCommand extends CommandBase {
    private final DriveTrain m_drive;
    private final DoubleSupplier m_left;
    private final DoubleSupplier m_right;

    public DriveCommand(DriveTrain drive, DoubleSupplier left, DoubleSupplier right) {
        m_drive = drive;
        m_left = left;
        m_right = right;
        addRequirements(m_drive);
    }

    @Override
    public void execute() {
        double r = m_right.getAsDouble();
        double l = m_left.getAsDouble();

        // Forward Snapping
        if ((Math.abs((l - r) / r) < 0.20 || Math.abs((r - l) / l) < 0.20) && ((l > 0.05 || l < -0.05) || (r > 0.05 || r < -0.05))) {
            double oldLeft = l;
            double oldRight = l;

            l = (oldLeft + oldRight) / 2;

            r = (oldLeft + oldRight) / 2;
        }

        // (a*(x^3)+(b-a)*x)*1.1
        double a = -0.2;
        double b = 0.9;

        // Modified curve
        r = (a * (r * r * r) + (b - a) * r) * 1.1;
        l = (a * (l * l * l) + (b - a) * l) * 1.1;

        m_drive.tankDrive(l, r);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}