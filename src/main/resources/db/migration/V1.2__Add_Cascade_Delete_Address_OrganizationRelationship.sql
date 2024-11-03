-- Migration Script: Cascade deletions for organization and user

BEGIN;

-- Step 1: Modify organization_relationship to cascade delete on organization deletions

-- Drop existing foreign key constraints on organization_relationship
ALTER TABLE organization_relationship
DROP CONSTRAINT IF EXISTS organization_relationship_contractor_fk;

ALTER TABLE organization_relationship
DROP CONSTRAINT IF EXISTS organization_relationship_subcontractor_fk;

-- Add foreign key constraint with ON DELETE CASCADE for contractor_id
ALTER TABLE organization_relationship
ADD CONSTRAINT organization_relationship_contractor_fk
FOREIGN KEY (contractor_id)
REFERENCES organization(organization_id)
ON DELETE CASCADE;

-- Add foreign key constraint with ON DELETE CASCADE for subcontractor_id
ALTER TABLE organization_relationship
ADD CONSTRAINT organization_relationship_subcontractor_fk
FOREIGN KEY (subcontractor_id)
REFERENCES organization(organization_id)
ON DELETE CASCADE;

-- Step 2: Create function and trigger to delete address when an organization is deleted

-- Create the function to delete address when an organization is deleted
CREATE OR REPLACE FUNCTION delete_address_on_organization_delete()
RETURNS TRIGGER AS $$
BEGIN
    DELETE FROM address WHERE address_id = OLD.address_id;
    RETURN OLD;
END;
$$ LANGUAGE plpgsql;

-- Create the trigger on the organization table
CREATE TRIGGER trg_delete_address_after_organization_delete
AFTER DELETE ON organization
FOR EACH ROW
WHEN (OLD.address_id IS NOT NULL)
EXECUTE FUNCTION delete_address_on_organization_delete();

-- Step 3: Create function and trigger to delete address when a user is deleted

-- Create the function to delete address when a user is deleted
CREATE OR REPLACE FUNCTION delete_address_on_user_delete()
RETURNS TRIGGER AS $$
BEGIN
    DELETE FROM address WHERE address_id = OLD.address_id;
    RETURN OLD;
END;
$$ LANGUAGE plpgsql;

-- Create the trigger on the users table
CREATE TRIGGER trg_delete_address_after_user_delete
AFTER DELETE ON users
FOR EACH ROW
WHEN (OLD.address_id IS NOT NULL)
EXECUTE FUNCTION delete_address_on_user_delete();

COMMIT;
