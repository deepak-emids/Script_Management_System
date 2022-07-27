Script Management System

2 types of users - Admin, Writer

API (only admin access)
1. Add Writer (name (non-editable), age, gender(male, female, other -> should give error if something else provided))
3. Edit Writer

API (only Writer access)
1. Add Screen Play (name, genre, description)
2. Delete Screen Play (by id)

API (public)
1. Get Writer info (by id) with screen play.
2. Get screen play info (by id) with Writer (only names).
