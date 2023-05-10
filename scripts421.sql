AFTER TABLE student ADD CONSTRAINT age_constraint CHECK ( age >= 16 );

AFTER TABLE student ADD CONSTRAINT name_unique UNIQUE(name);

AFTER TABLE student ALTER COLUMN name SET NOT NULL;

AFTER TABLE faculty ADD CONSTRAINT name_color_unique UNIQUE(name, color);

AFTER TABLE student ALTER COLUMN age SET DEFAULT 20;