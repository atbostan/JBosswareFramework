INSERT INTO public.users(
	id, creation_time,creator_user_id,modifier_user_id,email,is_active,is_not_locked,last_name,name,password,user_name)
	VALUES (101, now(),101,101,'admin@superadmin.com',true,true,'boss','admin','$2a$10$Vp1v5VfSYnb.eRXxf4H66OGEsiIm.9cpHFC.RfJjTLTcJ2WJss/ES','jbossAdmin');

INSERT INTO public.roles(
	 id,creation_time,creator_user_id,role_name, users_id)
	VALUES (102 ,now(),101,'SUPER_ADMIN',101);

INSERT INTO public.authorities(
	id, creation_time,creator_user_id, auth_name, roles_id)
	VALUES (103,now(),101,'CRUD',102);