-- Create stored procedure to truncate all tables in DB with name 'S2'
CREATE OR REPLACE FUNCTION truncate_all_tables()
 RETURNS INTEGER AS $$
DECLARE
    rec record;
    i INTEGER := 0 ;
BEGIN
 FOR rec IN
        SELECT table_name
        FROM information_schema.columns
        WHERE table_catalog = 'S2'
	AND table_schema = 'public'
    LOOP
        EXECUTE format('TRUNCATE TABLE %I CASCADE;',
            rec.table_name);
    END LOOP;
 RETURN i ;
END ;
$$ LANGUAGE plpgsql;
-- Example call: SELECT truncate_all_tables();


-- Truncate all tables without creating stored procedure using PLPGSQL (requires Postgres 9.0 or later)
DO $$
DECLARE rec record;
BEGIN
    FOR rec IN
        SELECT table_name
        FROM information_schema.columns
        WHERE table_catalog = 'S2'
	AND table_schema = 'public'
    LOOP
        EXECUTE format('TRUNCATE TABLE %I CASCADE;',
            rec.table_name);
    END LOOP;
END $$;


-- Get all user-defined tables in DB with name 'S2'
-- SELECT table_name
-- FROM information_schema.tables
-- WHERE table_catalog = 'S2'
-- AND table_schema = 'public';