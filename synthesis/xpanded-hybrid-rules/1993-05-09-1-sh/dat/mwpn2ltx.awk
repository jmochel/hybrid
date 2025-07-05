# MWPN2LTX
#
# A script to translate melee weapon data files to latex files
#
# 01/20/92 jsm	Created


BEGIN {

	# Defines some variables

	CoeffOfRestitution["CR"] = 0.64;
	CoeffOfRestitution["PRC"] = 0.18;
	CoeffOfRestitution["CT"] = 0.40;

	BasePower = 4500;
	TwoHandPowerBonus = 2.0;

	TargetMass = 7;
	ArmLengthOffset = 0.35;

	HammerFistSpeed = 6;
	HammerFistSpeedFactor = 0.25;
	SpeedOffset = 0;
}

/^.BeginWeapon/ {
	$1 = "";
	Name = $0;
}

/^.Type/ {
	CoeffOfRes = CoeffOfRestitution[$2];
}

/^.Length/ {
	L = $2;
}

/^.Mass/ {
	M = $2;
}

/^.Symmetry/ {
	Sym = $2;
}

/^.Hand/ {
	Hand = $2;
}

/^.TI/ {
	TI = $2;
}

/^.BeginTLB/ {
}

/^.EndTLB/ {
}

/^.BeginDesc/ {
}

/^.EndDesc/ {
}


/^.BeginCmt/ {
}

/^.EndCmt/ {

	# Determine the effective length

	if ( Sym == 2) {
		EffLength = (L/Sym) + ArmLengthOffset;
	}
	else {
		EffLength = L + ArmLengthOffset;
	}

	# If the weapon is two handed up the power output

	if ( Hand == 2) {
		Power = BasePower * TwoHandPowerBonus;
	}
	else {
		Power = BasePower;
	}

	Vsquared = ((Power * EffLength )/(M))^0.66;
	EnergyOfDeformation = ((CoeffOfRes * (M+7) * TargetMass)/((M+7) + TargetMass)) * (Vsquared);
	Damage = EnergyOfDeformation^0.42;

	SpeedFactor = 1.57 * ((Power * EffLength)/(M+7))^-0.33333;

	Speed = (HammerFistSpeedFactor * HammerFistSpeed)/SpeedFactor;

	printf("%-35s spd:%3.1d Damage %3.1d\n", Name, Speed, Damage);
}

