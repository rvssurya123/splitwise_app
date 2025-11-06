
import React from 'react';
import { useSplitwise } from '../contexts/SplitwiseContext';

interface HeaderProps {
    onAddExpense: () => void;
    onSettleUp: () => void;
}

export const Header: React.FC<HeaderProps> = ({ onAddExpense, onSettleUp }) => {
  const { currentUser } = useSplitwise();

  return (
    <header className="bg-white dark:bg-gray-800 shadow-sm p-4 flex justify-between items-center">
      <h1 className="text-2xl font-bold text-primary dark:text-white">SplitEase</h1>
      <div className="flex items-center space-x-2 md:space-x-4">
        <button
          onClick={onAddExpense}
          className="bg-danger hover:bg-danger-hover text-white font-bold py-2 px-4 rounded-lg text-sm md:text-base"
        >
          Add expense
        </button>
        <button
          onClick={onSettleUp}
          className="bg-secondary hover:bg-secondary-hover text-white font-bold py-2 px-4 rounded-lg text-sm md:text-base"
        >
          Settle up
        </button>
        <div className="flex items-center space-x-3">
          <img src={currentUser.avatarUrl} alt={currentUser.name} className="w-10 h-10 rounded-full" />
        </div>
      </div>
    </header>
  );
};
