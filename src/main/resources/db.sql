--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.10
-- Dumped by pg_dump version 9.4.10
-- Started on 2017-04-27 19:49:17

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 1 (class 3079 OID 11855)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2011 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 173 (class 1259 OID 16474)
-- Name: matches; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE matches (
    id integer NOT NULL,
    player_id integer,
    score integer,
    match_time timestamp without time zone
);


ALTER TABLE matches OWNER TO postgres;

--
-- TOC entry 174 (class 1259 OID 16477)
-- Name: Match_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "Match_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "Match_id_seq" OWNER TO postgres;

--
-- TOC entry 2012 (class 0 OID 0)
-- Dependencies: 174
-- Name: Match_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "Match_id_seq" OWNED BY matches.id;


--
-- TOC entry 175 (class 1259 OID 16479)
-- Name: players; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE players (
    id integer NOT NULL,
    nick_name character varying(30),
    email character varying(30),
    password character(32)
);


ALTER TABLE players OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 16482)
-- Name: Player_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE "Player_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "Player_id_seq" OWNER TO postgres;

--
-- TOC entry 2013 (class 0 OID 0)
-- Dependencies: 176
-- Name: Player_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE "Player_id_seq" OWNED BY players.id;


--
-- TOC entry 1887 (class 2604 OID 16484)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY matches ALTER COLUMN id SET DEFAULT nextval('"Match_id_seq"'::regclass);


--
-- TOC entry 1888 (class 2604 OID 16485)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY players ALTER COLUMN id SET DEFAULT nextval('"Player_id_seq"'::regclass);


--
-- TOC entry 1891 (class 2606 OID 16487)
-- Name: match_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY matches
    ADD CONSTRAINT match_id PRIMARY KEY (id);


--
-- TOC entry 1893 (class 2606 OID 16489)
-- Name: player_id; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY players
    ADD CONSTRAINT player_id PRIMARY KEY (id);


--
-- TOC entry 1889 (class 1259 OID 16490)
-- Name: fki_player_id; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX fki_player_id ON matches USING btree (player_id);


--
-- TOC entry 1894 (class 2606 OID 16496)
-- Name: player_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY matches
    ADD CONSTRAINT player_id FOREIGN KEY (player_id) REFERENCES players(id) ON DELETE CASCADE;


--
-- TOC entry 2010 (class 0 OID 0)
-- Dependencies: 7
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2017-04-27 19:49:18

--
-- PostgreSQL database dump complete
--

