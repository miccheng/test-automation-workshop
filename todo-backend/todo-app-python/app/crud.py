from sqlalchemy.orm import Session
from sqlalchemy import delete
from . import models, schemas


def get_todos(db: Session, skip: int = 0, limit: int = 10):
    return db.query(models.TodoItem).offset(skip).limit(limit).all()


def get_todo_by_id(db: Session, todo_id: int):
    return db.query(models.TodoItem).filter(models.TodoItem.id == todo_id).first()


def create_todo(db: Session, todo: schemas.TodoCreate):
    db_todo = models.TodoItem(**todo.dict())
    db.add(db_todo)
    db.commit()
    db.refresh(db_todo)
    return db_todo


def update_todo(db: Session, todo_id: int, todo: schemas.TodoUpdate):
    db_todo = get_todo_by_id(db, todo_id)
    if db_todo:
        db_todo.task = todo.task
        db_todo.completed = todo.completed
        db.commit()
        db.refresh(db_todo)
    return db_todo


def delete_todo(db: Session, todo_id: int):
    db_todo = get_todo_by_id(db, todo_id)
    if db_todo:
        db.delete(db_todo)
        db.commit()
    return db_todo


def delete_completed_todos(db: Session):
    stmt = delete(models.TodoItem).where(
        models.TodoItem.completed.__eq__(True))
    db.execute(stmt)
    db.commit()
