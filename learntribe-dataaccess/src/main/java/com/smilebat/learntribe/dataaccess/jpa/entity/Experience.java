package com.smilebat.learntribe.dataaccess.jpa.entity;

/**
 * Generic interface for any experience
 *
 * <p>Copyright &copy; 2022 Smile .Bat
 *
 * @author Pai,Sai Nandan
 */
public interface Experience {
  /**
   * Retrieves the primary key id of the table.
   *
   * @return the unique id of the entity.
   */
  Long getId();

  /**
   * Sets the primary key id of the table.
   *
   * @param id the id
   */
  void setId(Long id);
}
