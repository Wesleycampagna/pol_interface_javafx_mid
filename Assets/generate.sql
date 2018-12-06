-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema policia_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema policia_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `policia_db` DEFAULT CHARACTER SET utf8 ;
USE `policia_db` ;

-- -----------------------------------------------------
-- Table `policia_db`.`Pessoa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policia_db`.`Pessoa` (
  `cpf` VARCHAR(11) NOT NULL,
  `nome` VARCHAR(70) NOT NULL,
  `rg` VARCHAR(20) NULL DEFAULT NULL,
  `data_nascimento` DATE NOT NULL,
  `nome_mae` VARCHAR(45) NULL DEFAULT NULL,
  `nome_pai` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`cpf`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `policia_db`.`evidencia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policia_db`.`evidencia` (
  `id_evidencia` INT(15) UNSIGNED NOT NULL AUTO_INCREMENT,
  `descc` VARCHAR(100) NULL,
  PRIMARY KEY (`id_evidencia`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `policia_db`.`arma`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policia_db`.`arma` (
  `id_evidencia` INT(15) UNSIGNED NOT NULL,
  `numero_serie` INT(20) NOT NULL,
  `calibre` DOUBLE NOT NULL,
  `fabricante` VARCHAR(30) NULL DEFAULT NULL,
  `modelo` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`id_evidencia`, `numero_serie`),
  CONSTRAINT `fk_evidencia_arma`
    FOREIGN KEY (`id_evidencia`)
    REFERENCES `policia_db`.`evidencia` (`id_evidencia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `policia_db`.`celular`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policia_db`.`celular` (
  `id_evidencia` INT(20) UNSIGNED NOT NULL,
  `imei` VARCHAR(20) NOT NULL,
  `nro_celular` VARCHAR(15) NOT NULL,
  `fabricante` VARCHAR(30) NULL DEFAULT NULL,
  `modelo` VARCHAR(30) NOT NULL,
  `cpf_titular` VARCHAR(45) NULL DEFAULT NULL,
  `nome_titular` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id_evidencia`, `imei`),
  CONSTRAINT `fk_evidencia_cel`
    FOREIGN KEY (`id_evidencia`)
    REFERENCES `policia_db`.`evidencia` (`id_evidencia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `policia_db`.`cidade_estado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policia_db`.`cidade_estado` (
  `id_cidade_estado` INT(15) UNSIGNED NOT NULL AUTO_INCREMENT,
  `cidade` VARCHAR(30) NOT NULL,
  `estado` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id_cidade_estado`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `policia_db`.`endereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policia_db`.`endereco` (
  `id_endereco` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `logradouro` VARCHAR(45) NOT NULL,
  `numero` INT(5) NOT NULL,
  `bairro` VARCHAR(45) NOT NULL,
  `complemento` VARCHAR(50) NULL DEFAULT NULL,
  `cep` INT(10) NULL DEFAULT NULL,
  `referencia` VARCHAR(45) NULL DEFAULT NULL,
  `cid_est` INT(15) UNSIGNED NOT NULL,
  PRIMARY KEY (`id_endereco`),
  INDEX `fk_cid_est_idx` (`cid_est` ASC),
  CONSTRAINT `fk_cid_est_end`
    FOREIGN KEY (`cid_est`)
    REFERENCES `policia_db`.`cidade_estado` (`id_cidade_estado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `policia_db`.`naturalidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policia_db`.`naturalidade` (
  `id_natural` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `cid_estado` INT(15) UNSIGNED NOT NULL,
  PRIMARY KEY (`id_natural`),
  INDEX `fk_cid_est_idx` (`cid_estado` ASC),
  CONSTRAINT `fk_cid_est_nat`
    FOREIGN KEY (`cid_estado`)
    REFERENCES `policia_db`.`cidade_estado` (`id_cidade_estado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `policia_db`.`cidadao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policia_db`.`cidadao` (
  `cpf` VARCHAR(11) NOT NULL,
  `alcunha` VARCHAR(32) NOT NULL,
  `telefone` VARCHAR(15) NULL DEFAULT NULL,
  `status` VARCHAR(12) NOT NULL,
  `naturalidade` INT(10) UNSIGNED NOT NULL,
  `endereco` INT(11) UNSIGNED NOT NULL,
  PRIMARY KEY (`cpf`),
  INDEX `fk_endereco_idx` (`endereco` ASC),
  INDEX `fk_naturalidade_idx` (`naturalidade` ASC),
  CONSTRAINT `fk_cpf_cid`
    FOREIGN KEY (`cpf`)
    REFERENCES `policia_db`.`Pessoa` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_endereco_cid`
    FOREIGN KEY (`endereco`)
    REFERENCES `policia_db`.`endereco` (`id_endereco`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_naturalidade_cid`
    FOREIGN KEY (`naturalidade`)
    REFERENCES `policia_db`.`naturalidade` (`id_natural`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `policia_db`.`delegacia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policia_db`.`delegacia` (
  `id_delegacia` INT(5) NOT NULL AUTO_INCREMENT,
  `sigla` VARCHAR(8) NOT NULL,
  `nome` VARCHAR(80) NOT NULL,
  PRIMARY KEY (`id_delegacia`, `sigla`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `policia_db`.`policial`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policia_db`.`policial` (
  `cpf` VARCHAR(11) NOT NULL,
  `numero_matricula` VARCHAR(20) NOT NULL,
  `telefone` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`numero_matricula`, `cpf`),
  INDEX `fk_cpf_idx` (`cpf` ASC),
  CONSTRAINT `fk_cpf_pol`
    FOREIGN KEY (`cpf`)
    REFERENCES `policia_db`.`Pessoa` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `policia_db`.`delegado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policia_db`.`delegado` (
  `numero_matricula` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`numero_matricula`),
  CONSTRAINT `fk_nro_mat_del`
    FOREIGN KEY (`numero_matricula`)
    REFERENCES `policia_db`.`policial` (`numero_matricula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `policia_db`.`dimensao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policia_db`.`dimensao` (
  `id_dim` INT(15) NOT NULL AUTO_INCREMENT,
  `largura` DOUBLE NOT NULL,
  `comprimento` DOUBLE NOT NULL,
  `altura` DOUBLE NOT NULL,
  PRIMARY KEY (`id_dim`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `policia_db`.`ocorrencia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policia_db`.`ocorrencia` (
  `id_ocorrencia` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `data` DATE NOT NULL,
  `hora` TIME NOT NULL,
  `del_responsavel` VARCHAR(20) NOT NULL,
  `infracao` VARCHAR(45) NOT NULL,
  `status` VARCHAR(10) NOT NULL,
  `id_endereco` INT(11) UNSIGNED NOT NULL,
  `delegacia` INT(5) NOT NULL,
  PRIMARY KEY (`id_ocorrencia`),
  INDEX `fk_delegacia_idx` (`delegacia` ASC),
  INDEX `fk_delegado_idx` (`del_responsavel` ASC),
  INDEX `fk_endereco_idx` (`id_endereco` ASC),
  CONSTRAINT `fk_delegacia_oc`
    FOREIGN KEY (`delegacia`)
    REFERENCES `policia_db`.`delegacia` (`id_delegacia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_delegado_oc`
    FOREIGN KEY (`del_responsavel`)
    REFERENCES `policia_db`.`policial` (`numero_matricula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_endereco_oc`
    FOREIGN KEY (`id_endereco`)
    REFERENCES `policia_db`.`endereco` (`id_endereco`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `policia_db`.`equipe`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policia_db`.`equipe` (
  `nro_matricula` VARCHAR(20) NOT NULL,
  `id_ocorrencia` INT(11) UNSIGNED NOT NULL,
  PRIMARY KEY (`nro_matricula`, `id_ocorrencia`),
  INDEX `fk_oc_idx` (`id_ocorrencia` ASC),
  CONSTRAINT `fk_oc_eq`
    FOREIGN KEY (`id_ocorrencia`)
    REFERENCES `policia_db`.`ocorrencia` (`id_ocorrencia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_policial_eq`
    FOREIGN KEY (`nro_matricula`)
    REFERENCES `policia_db`.`policial` (`numero_matricula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `policia_db`.`evidencia_ocorrencia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policia_db`.`evidencia_ocorrencia` (
  `id_evidencia` INT(15) UNSIGNED NOT NULL,
  `id_ocorrencia` INT(11) UNSIGNED NOT NULL,
  PRIMARY KEY (`id_evidencia`, `id_ocorrencia`),
  INDEX `fk_oc_idx` (`id_ocorrencia` ASC),
  CONSTRAINT `fk_evidencia_evoc`
    FOREIGN KEY (`id_evidencia`)
    REFERENCES `policia_db`.`evidencia` (`id_evidencia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_oc_evoc`
    FOREIGN KEY (`id_ocorrencia`)
    REFERENCES `policia_db`.`ocorrencia` (`id_ocorrencia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `policia_db`.`nacionalidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policia_db`.`nacionalidade` (
  `id_nacionalidade` INT(11) NOT NULL AUTO_INCREMENT,
  `nacionalidade` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`id_nacionalidade`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `policia_db`.`nac_pessoa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policia_db`.`nac_pessoa` (
  `cpf` VARCHAR(11) NOT NULL,
  `id_nacionalidade` INT(15) NOT NULL,
  PRIMARY KEY (`cpf`, `id_nacionalidade`),
  INDEX `fk_nac_nac_pes_idx` (`id_nacionalidade` ASC),
  CONSTRAINT `fk_nac_nac_pes`
    FOREIGN KEY (`id_nacionalidade`)
    REFERENCES `policia_db`.`nacionalidade` (`id_nacionalidade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_pessoa_nac_pes`
    FOREIGN KEY (`cpf`)
    REFERENCES `policia_db`.`Pessoa` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `policia_db`.`objeto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policia_db`.`objeto` (
  `id_evidencia` INT(15) UNSIGNED NOT NULL,
  `id_objeto` INT(15) NOT NULL,
  `nome` VARCHAR(30) NOT NULL,
  `desc` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`id_evidencia`, `id_objeto`),
  CONSTRAINT `fk_evidencia_obj`
    FOREIGN KEY (`id_evidencia`)
    REFERENCES `policia_db`.`evidencia` (`id_evidencia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `policia_db`.`pessoa_envolvida`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policia_db`.`pessoa_envolvida` (
  `cpf` VARCHAR(11) NOT NULL,
  `fk_oc` INT(11) UNSIGNED NOT NULL,
  `tipo` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`cpf`, `fk_oc`),
  INDEX `fk_oc_idx` (`fk_oc` ASC),
  CONSTRAINT `fk_cidadao_pe`
    FOREIGN KEY (`cpf`)
    REFERENCES `policia_db`.`cidadao` (`cpf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_oc_pe`
    FOREIGN KEY (`fk_oc`)
    REFERENCES `policia_db`.`ocorrencia` (`id_ocorrencia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `policia_db`.`substancia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policia_db`.`substancia` (
  `id_evidencia` INT(15) UNSIGNED NOT NULL,
  `id_subs` VARCHAR(10) NOT NULL,
  `tipo_substancia` VARCHAR(30) NOT NULL,
  `peso` DOUBLE NOT NULL,
  `embalagem` VARCHAR(30) NOT NULL,
  `quantidade` INT(15) NOT NULL,
  `dimensao` INT(15) NULL,
  PRIMARY KEY (`id_evidencia`, `id_subs`),
  INDEX `fk_dim_sub_idx` (`dimensao` ASC),
  CONSTRAINT `fk_evidencia_sub`
    FOREIGN KEY (`id_evidencia`)
    REFERENCES `policia_db`.`evidencia` (`id_evidencia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_dim_sub`
    FOREIGN KEY (`dimensao`)
    REFERENCES `policia_db`.`dimensao` (`id_dim`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `policia_db`.`veiculo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `policia_db`.`veiculo` (
  `id_evidencia` INT(15) UNSIGNED NOT NULL,
  `numero_chassi` VARCHAR(30) NOT NULL,
  `placa` VARCHAR(8) NOT NULL,
  `ano_fabricacao` YEAR NOT NULL,
  `ano_modelo` YEAR NOT NULL,
  `fabricante` VARCHAR(30) NOT NULL,
  `modelo` VARCHAR(30) NOT NULL,
  `cor` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`numero_chassi`, `id_evidencia`),
  INDEX `fk_veiculo_idx` (`id_evidencia` ASC),
  CONSTRAINT `fk_veiculo_veic`
    FOREIGN KEY (`id_evidencia`)
    REFERENCES `policia_db`.`evidencia` (`id_evidencia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
