from sqlalchemy import create_engine
# from sqlalchemy.ext.declarative import declarative_base
# from sqlalchemy.orm import sessionmaker
from sqlalchemy.orm import declarative_base, sessionmaker 

SQLALCHEMY_DATABASE_URL = "sqlite:///./todos.db"
SQLALCHEMY_TEST_DATABASE_URL = "sqlite:///./test_todos.db"

engine = create_engine(SQLALCHEMY_DATABASE_URL, connect_args={"check_same_thread": False})
test_engine = create_engine(SQLALCHEMY_TEST_DATABASE_URL, connect_args={"check_same_thread": False})

SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)
TestSessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=test_engine)

Base = declarative_base()

def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()

def get_test_db():
    db = TestSessionLocal()
    try:
        yield db
    finally:
        db.close()
