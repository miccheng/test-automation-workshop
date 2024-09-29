from sqlalchemy import Column, Integer, String, Boolean
from .database import Base


class TodoItem(Base):
    __tablename__ = "todos"

    id = Column(Integer, primary_key=True, index=True)
    task = Column(String, index=True)
    completed = Column(Boolean, default=False)
