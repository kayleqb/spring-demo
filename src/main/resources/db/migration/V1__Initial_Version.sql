CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE public.demo
(
    id   uuid DEFAULT gen_random_uuid() NOT NULL,
    name character varying(100) COLLATE pg_catalog."default",
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7 UNIQUE (name)
) WITH (OIDS = FALSE)
  TABLESPACE pg_default;

create index users_id on demo (id);
create index users_email on demo (name);
