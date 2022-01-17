package bhs.devilbotz.commands.shooter;

import bhs.devilbotz.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class Shoot extends CommandBase {
    private final Shooter shooter;
    private double m_speed;

    public Shoot(Shooter shooter, double speed) {
        this.shooter = shooter;
        this.m_speed = speed;
        addRequirements(shooter);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}